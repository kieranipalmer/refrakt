package dev.shanty.lifx

import dev.shanty.lifx.actors.Actor
import dev.shanty.lifx.actors.ActorFactory
import dev.shanty.lifx.actors.actor
import dev.shanty.lifx.messages.GetColour
import dev.shanty.lifx.messages.LifxCommand
import dev.shanty.lifx.messages.LifxEvent
import dev.shanty.lifx.messages.SetColour
import dev.shanty.lifx.models.HsbkColour
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import java.net.InetAddress
import java.time.Instant
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration

class ActorManager(parentScope: CoroutineScope) {
    private val actorManagerJob = SupervisorJob(parentScope.coroutineContext[Job])
    private val actorCoroutineContext: CoroutineContext = parentScope.coroutineContext + actorManagerJob

    private val knownActors = mutableMapOf<String, Actor<*, *>>()

    fun <TIN, TOUT> uniqueActor(key: String, init: ActorFactory<TIN, TOUT>): Actor<TIN, TOUT> {
        return knownActors.getOrPut(key) {
            actor(init)
        } as Actor<TIN, TOUT>
    }

    fun <TIN, TOUT> uniqueActor(key: String, inFlow: Flow<TIN>, init: ActorFactory<TIN, TOUT>): Actor<TIN, TOUT> {
        val found = knownActors.getOrPut(key) {
            actor<TIN, TOUT>(inFlow, init)
        }

        return found as Actor<TIN, TOUT>
    }

    fun <TIN, TOUT> actor(init: ActorFactory<TIN, TOUT>) = actor<TIN, TOUT>(actorCoroutineContext, init)
    fun <TIN, TOUT> actor(inFlow: Flow<TIN>, init: ActorFactory<TIN, TOUT>) = actor<TIN, TOUT>(actorCoroutineContext, inFlow, init)
}

data class CommandEnvelope(
    val target: InetAddress = InetAddress.getByName("255.255.255.255"),
    val payload: LifxCommand
)

private fun ActorManager.startLifxNetworkActor() = actor<CommandEnvelope, LifxEvent> {
    val lifxServer = LifxServer()

    onStart {
        lifxServer.start().collect {
            emit(it)
        }
    }

    process {
        lifxServer.sendCommand(it.payload, it.target)
    }
}

private fun ActorManager.startLifxDiscoveryActor(
    discoveryTime: Long,
    networkActor: Actor<CommandEnvelope, LifxEvent>
) = actor {
    val knownDevices = mutableSetOf<LifxEvent.StateService>()

    onStart {
        launch {
            while (isActive) {
                sendTo(Unit)
                delay(30000)
            }
        }
    }

    process {
        println("Running Discovery")

        networkActor.sendTo(CommandEnvelope(payload = LifxCommand.GetService))
        val discoveryResponses = networkActor.outbox.mapNotNull {
            it as? LifxEvent.StateService
        }.timeoutAfter(discoveryTime).toList()

        val newDevices = discoveryResponses
            .distinctBy { it.source }
            .filterNot {
                knownDevices.contains(it)
            }

        newDevices.forEach {
            knownDevices.add(it)
            emit(it)
        }
    }
}

private fun ActorManager.startDeviceManagerActor(
    discoveryActor: Actor<*, LifxEvent.StateService>,
    networkActor: Actor<CommandEnvelope, LifxEvent>
) = actor(discoveryActor.outbox) {

    process {
        val newActor = startDeviceActor(it.source, networkActor)
        emit(newActor)
    }
}

sealed interface DeviceActorInput {

    data class Event(val event: LifxEvent) : DeviceActorInput
    sealed interface Command : DeviceActorInput {
        data class SetColour(val colour: HsbkColour, val duration: Duration) : Command
    }
}

private fun ActorManager.startDeviceActor(
    deviceIp: InetAddress,
    networkActor: Actor<CommandEnvelope, LifxEvent>
) = uniqueActor<DeviceActorInput, LightActorState>(
    deviceIp.toString(),
    networkActor.outbox.filter { it.source == deviceIp }.map { DeviceActorInput.Event(it) }
) {

    var state = LightActorState(
        HsbkColour(0u, 0u, 0u, 0u),
        false,
        "",
        Instant.now()
    )

    onStart {
        println("Starting actor for device $deviceIp")
        launch {
            while (isActive) {
                networkActor.sendTo(CommandEnvelope(target = deviceIp, payload = GetColour))
                delay(5000)
            }
        }
    }

    suspend fun processEvent(event: LifxEvent) {
        val currentState = state
        when (event) {
            is LifxEvent.Acknowledgement -> {}
            is LifxEvent.LightState -> {
                state = state.copy(
                    colour = HsbkColour(event.hue, event.saturation, event.brightness, event.kelvin),
                    label = event.label,
                    power = event.power,
                )
            }
            is LifxEvent.StateService -> {}
        }

        if (state != currentState) {
            state = state.copy(updatedAt = Instant.now())
            it.emit(state)
        }
    }

    suspend fun processCommand(command: DeviceActorInput.Command) = when (command) {
        is DeviceActorInput.Command.SetColour -> networkActor.sendTo(
            CommandEnvelope(deviceIp, SetColour(command.colour, command.duration.inWholeMilliseconds.toUInt()))
        )
    }

    process { input ->
        when (input) {
            is DeviceActorInput.Event -> processEvent(input.event)
            is DeviceActorInput.Command -> processCommand(input)
        }
    }
}

// Facade around Lifx Actor
class Lifx(actorManager: ActorManager) {
    private val lifxNetworkActor = actorManager.startLifxNetworkActor()
    private val lifxDiscoveryActor = actorManager.startLifxDiscoveryActor(5000, lifxNetworkActor)
    private val deviceDiscoveryActor = actorManager.startDeviceManagerActor(lifxDiscoveryActor, lifxNetworkActor)

    val discoveryEvents: Flow<Device> = deviceDiscoveryActor.outbox.map {
        Device.Light(it)
    }
}

sealed interface Device {
    class Light(
        private val actor: Actor<DeviceActorInput, LightActorState>,
    ) : Device {

        val stateEvents = actor.outbox.stateIn(
            actor, SharingStarted.Eagerly,
            LightActorState(
                HsbkColour(0u, 0u, 0u, 0u),
                false,
                "",
                Instant.now()
            )
        )

        val label: String = stateEvents.value.label

        suspend fun setColour(colour: HsbkColour, duration: Duration) {
            actor.sendTo(DeviceActorInput.Command.SetColour(colour, duration))
        }
    }
}

data class LightActorState(
    val colour: HsbkColour,
    val power: Boolean,
    val label: String,
    val updatedAt: Instant
)

fun <T> Flow<T>.timeoutAfter(duration: Long) = flow<T> {
    withTimeoutOrNull(duration) {
        this@timeoutAfter.collect { emit(it) }
    }
}
