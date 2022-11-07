package dev.shanty.refrakt

import dev.shanty.refrakt.actors.Actor
import dev.shanty.refrakt.actors.LightActorInput
import dev.shanty.refrakt.actors.LightActorState
import dev.shanty.refrakt.models.HsbkColour
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import java.time.Instant
import kotlin.time.Duration

sealed interface Device {
    class Light(
        private val actor: Actor<LightActorInput, LightActorState>,
    ) : Device {

        val stateEvents = actor.outbox.stateIn(
            actor,
            SharingStarted.Eagerly,
            LightActorState(
                HsbkColour(0u, 0u, 0u, 0u),
                false,
                "",
                Instant.now()
            )
        )

        val label: String = stateEvents.value.label

        /**
         * Sets the Colour of the Light
         *
         * @param colour The target colour of the light
         * @param duration The duration the transition of colour should occur over
         * @param waitForResponse Whether the coroutine should be suspended until the light has changed colour.
         * Warning: This can potentially hang indefinitely if the light does not respond.
         * It is recommended this function is wrapped in a timeout
         */
        suspend fun setColour(colour: HsbkColour, duration: Duration, waitForResponse: Boolean = false) {
            actor.sendTo(LightActorInput.Command.SetColour(colour, duration))
            if (waitForResponse) {
                actor.outbox.first { it.colour == colour }
            }
        }

        /**
         * Sets the Power of the Light
         *
         * @param power Boolean representing the desired power of the device. False for off and True for on
         * @param waitForResponse Whether the coroutine should be suspended until the light has changed power.
         * Warning: This can potentially hang indefinitely if the light does not respond.
         * It is recommended this function is wrapped in a timeout
         */
        suspend fun setPower(power: Boolean, waitForResponse: Boolean = false) {
            actor.sendTo(LightActorInput.Command.SetPower(power))
            if (waitForResponse) {
                actor.outbox.first { it.power == power }
            }
        }
    }
}
