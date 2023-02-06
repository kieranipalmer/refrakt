package dev.shanty.refrakt

import dev.shanty.akt.runLocalActorSystem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

fun main() = runLocalActorSystem {

    val lifx = Lifx(this)

    lifx.discoveryEvents.onEach {
        val light = (it as Device.Light)

        launch {
            light.stateEvents.collect {
                println(it)
            }
        }
    }.collect()
}
