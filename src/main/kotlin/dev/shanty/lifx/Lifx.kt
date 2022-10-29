package dev.shanty.lifx

import dev.shanty.lifx.messages.LifxCommand
import dev.shanty.lifx.messages.LifxEvent
import dev.shanty.lifx.messages.SetColour
import dev.shanty.lifx.models.HsbkColour
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

class Lifx(scope: CoroutineScope) {

    val discoveredDevices = mutableListOf<LifxDevice>()
    private val lifxServer = LifxServer()
    private val lifxEvents = flow<LifxEvent> {

    }


    suspend fun setAllLightsToColour(colour: HsbkColour, duration: Long = 500): Boolean {
        lifxServer.sendCommand(
            SetColour(
                colour,
                duration.toUInt(),
            )
        )

        withTimeoutOrNull(5000) {
            val lightResponses = lifxEvents.mapNotNull {
                it as? LifxEvent.LightState
            }.map {

            }.toList()
        }
    }


}

sealed interface LifxDevice

class Light(
    val macAddress: MacAddress
) : LifxDevice {


}
