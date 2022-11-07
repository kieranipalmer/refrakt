package dev.shanty.refrakt.actors

import dev.shanty.refrakt.messages.GetColour
import dev.shanty.refrakt.messages.LifxEvent
import dev.shanty.refrakt.messages.SetColour
import dev.shanty.refrakt.messages.SetPower
import dev.shanty.refrakt.models.HsbkColour
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.net.InetAddress
import java.time.Instant
import kotlin.time.Duration

internal fun ActorManager.startLightActor(
    lightIp: InetAddress,
    networkActor: Actor<NetworkCommandEnvelope, LifxEvent>
) = uniqueActor<LightActorInput, LightActorState>(
    lightIp.toString(),
    networkActor.outbox.filter { it.source == lightIp }.map { LightActorInput.Event(it) }
) {

    var state = LightActorState(
        HsbkColour(0u, 0u, 0u, 0u),
        false,
        "",
        Instant.now()
    )

    onStart {
        println("Starting actor for device $lightIp")
        launch {
            while (isActive) {
                networkActor.sendTo(NetworkCommandEnvelope(target = lightIp, payload = GetColour))
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

    suspend fun processCommand(command: LightActorInput.Command) = when (command) {
        is LightActorInput.Command.SetColour -> networkActor.sendTo(
            NetworkCommandEnvelope(lightIp, SetColour(command.colour, command.duration.inWholeMilliseconds.toUInt()))
        )

        is LightActorInput.Command.SetPower -> networkActor.sendTo(
            NetworkCommandEnvelope(lightIp, SetPower(command.power))
        )
    }

    process { input ->
        when (input) {
            is LightActorInput.Event -> processEvent(input.event)
            is LightActorInput.Command -> processCommand(input)
        }
    }
}

sealed interface LightActorInput {
    data class Event(val event: LifxEvent) : LightActorInput
    sealed interface Command : LightActorInput {
        data class SetColour(val colour: HsbkColour, val duration: Duration) : Command
        data class SetPower(val power: Boolean) : Command
    }
}

data class LightActorState(
    val colour: HsbkColour,
    val power: Boolean,
    val label: String,
    val updatedAt: Instant
)
