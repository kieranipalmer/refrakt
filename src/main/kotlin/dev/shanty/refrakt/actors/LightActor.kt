package dev.shanty.refrakt.actors

import dev.shanty.refrakt.messages.GetColour
import dev.shanty.refrakt.messages.LifxEvent
import dev.shanty.refrakt.messages.SetColour
import dev.shanty.refrakt.models.HsbkColour
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.net.InetAddress
import java.time.Instant
import kotlin.time.Duration

internal fun ActorManager.startDeviceActor(
    deviceIp: InetAddress,
    networkActor: Actor<NetworkCommandEnvelope, LifxEvent>
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
                networkActor.sendTo(NetworkCommandEnvelope(target = deviceIp, payload = GetColour))
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
            NetworkCommandEnvelope(deviceIp, SetColour(command.colour, command.duration.inWholeMilliseconds.toUInt()))
        )
    }

    process { input ->
        when (input) {
            is DeviceActorInput.Event -> processEvent(input.event)
            is DeviceActorInput.Command -> processCommand(input)
        }
    }
}

sealed interface DeviceActorInput {
    data class Event(val event: LifxEvent) : DeviceActorInput
    sealed interface Command : DeviceActorInput {
        data class SetColour(val colour: HsbkColour, val duration: Duration) : Command
    }
}

data class LightActorState(
    val colour: HsbkColour,
    val power: Boolean,
    val label: String,
    val updatedAt: Instant
)
