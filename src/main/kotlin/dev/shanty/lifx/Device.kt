package dev.shanty.lifx

import dev.shanty.lifx.actors.Actor
import dev.shanty.lifx.actors.DeviceActorInput
import dev.shanty.lifx.actors.LightActorState
import dev.shanty.lifx.models.HsbkColour
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import java.time.Instant
import kotlin.time.Duration

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
