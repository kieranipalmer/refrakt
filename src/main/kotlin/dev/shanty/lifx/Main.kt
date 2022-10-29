package dev.shanty.lifx

import dev.shanty.lifx.messages.SetColour
import dev.shanty.lifx.models.HsbkColour
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val server = LifxServer()

    launch {
        delay(500)
        server.sendCommand(
            SetColour(
                HsbkColour(
                    hue = 240,
                    saturation = 0f,
                    brightness = 1f,
                    kelvin = 0.25f,
                ),
                duration = 500u,
            )
        )
    }

    server.start().collect {
        println(it)
    }
}