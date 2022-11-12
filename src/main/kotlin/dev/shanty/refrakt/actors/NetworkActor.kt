package dev.shanty.refrakt.actors

import dev.shanty.akt.actor.Actor
import dev.shanty.akt.actor.actor
import dev.shanty.refrakt.LifxServer
import dev.shanty.refrakt.messages.LifxCommand
import dev.shanty.refrakt.messages.LifxEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.InetAddress

internal fun CoroutineScope.startLifxNetworkActor(): Actor<NetworkCommandEnvelope, LifxEvent> {
    val lifxServer = LifxServer()

    val actor = actor<NetworkCommandEnvelope, LifxEvent> {
        lifxServer.sendCommand(it.payload, it.target)
    }

    actor.launch {
        lifxServer.start().collect {
            actor.emit(it)
        }
    }

    return actor
}

data class NetworkCommandEnvelope(
    val target: InetAddress = InetAddress.getByName("255.255.255.255"),
    val payload: LifxCommand
)
