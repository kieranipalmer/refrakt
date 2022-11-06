package dev.shanty.lifx

import dev.shanty.lifx.messages.SetColour
import dev.shanty.lifx.models.HsbkColour
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        val actorManager = ActorManager(this)
        val lifx = Lifx(actorManager)
        lifx.discoveryEvents.onEach {
            val light = (it as Device.Light)
            println("Discovered Light ${light.label}")

            launch {
                light.stateEvents.collect {
                    println(it)
                }
            }
        }.collect()
    }
}
