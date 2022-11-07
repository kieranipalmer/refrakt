package dev.shanty.refrakt

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        val lifx = Lifx(this)
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
