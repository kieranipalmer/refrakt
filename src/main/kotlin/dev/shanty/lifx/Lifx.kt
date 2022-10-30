package dev.shanty.lifx

import dev.shanty.lifx.actors.Actor
import dev.shanty.lifx.actors.ActorBuilder
import dev.shanty.lifx.actors.ActorFactory
import dev.shanty.lifx.actors.actor
import dev.shanty.lifx.actors.collectToChannel
import dev.shanty.lifx.messages.GetColour
import dev.shanty.lifx.messages.LifxCommand
import dev.shanty.lifx.messages.LifxEvent
import dev.shanty.lifx.models.HsbkColour
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import java.net.InetAddress
import java.time.Instant
import kotlin.coroutines.CoroutineContext

class ActorManager(parentScope: CoroutineScope) {
    private val actorManagerJob = SupervisorJob(parentScope.coroutineContext[Job])
    val actorCoroutineContext: CoroutineContext = parentScope.coroutineContext + actorManagerJob

    fun <TIN, TOUT> actor(init: ActorFactory<TIN, TOUT>) = CoroutineScope(actorCoroutineContext).actor<TIN, TOUT>(actorCoroutineContext, init)
    fun <TIN, TOUT> actor(inFlow: Flow<TIN>, init: ActorFactory<TIN, TOUT>) = CoroutineScope(actorCoroutineContext).actor<TIN, TOUT>(actorCoroutineContext, inFlow, init)
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
) = actor<Unit, LifxDevice> {
    val knownDevices = mutableMapOf<InetAddress, LifxDevice>()

    onStart {
        val tickerChannel = ticker(30000, 0, context = this.coroutineContext)
        tickerChannel.receiveAsFlow()
            .onEach { sendTo(Unit) }
            .collect()
    }

    process {
        networkActor.sendTo(CommandEnvelope(payload = LifxCommand.GetService))
        val discoveryResponses = networkActor.outbox.mapNotNull {
            it as? LifxEvent.StateService
        }.timeoutAfter(discoveryTime).toList()

        val newDevices = discoveryResponses.filter {
            !knownDevices.containsKey(it.source)
        }.map {
            Light(it.source)
        }

        newDevices.forEach {
            knownDevices[it.ipAddress] = it
            emit(it)
        }
    }
}

//Facade around Lifx Actor
class Lifx(private val actorManager: ActorManager) {

    private val lifxNetworkActor = actorManager.startLifxNetworkActor()
    private val lifxDiscoveryActor = actorManager.startLifxDiscoveryActor(5000, lifxNetworkActor)



    private suspend fun launchLightActor(device: Light): Actor<LifxEvent, LightActorState> = coroutineScope {
        val eventFlow = lifxNetworkActor.outbox.filter {
            it.source == device.ipAddress
        }

        actorManager.actor(eventFlow) {
            var state = LightActorState(
                HsbkColour(0u, 0u, 0u, 0u),
                false,
                "",
                Instant.now()
            )

            onStart {
                println("Starting actor for device $device")
                lifxNetworkActor.sendTo(CommandEnvelope(target = device.ipAddress, payload = GetColour))
            }

            process { event ->
                val currentState = state
                when(event) {
                    is LifxEvent.Acknowledgement -> {}
                    is LifxEvent.LightState -> {
                        state = state.copy(
                            colour = HsbkColour(event.hue, event.saturation, event.brightness, event.kelvin),
                            label = event.label,
                        )
                    }
                    is LifxEvent.StateService -> {}
                }

                if(state != currentState) {
                    state = state.copy(updatedAt = Instant.now())
                    outbox.send(state)
                }
            }
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

sealed interface LifxDevice {
    val ipAddress: InetAddress
}

class Light(
    override val ipAddress: InetAddress,
) : LifxDevice {

    override fun toString(): String {
        return "Light: $ipAddress"
    }
}

