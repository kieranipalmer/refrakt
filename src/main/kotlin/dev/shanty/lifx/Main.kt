package dev.shanty.lifx

import dev.shanty.lifx.messages.SetColour
import dev.shanty.lifx.models.HsbkColour
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        val lifx = Lifx(this)
    }
}
