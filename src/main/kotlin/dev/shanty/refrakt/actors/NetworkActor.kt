package dev.shanty.refrakt.actors

import dev.shanty.refrakt.LifxServer
import dev.shanty.refrakt.messages.LifxCommand
import dev.shanty.refrakt.messages.LifxEvent
import java.net.InetAddress

internal fun ActorManager.startLifxNetworkActor() = actor<NetworkCommandEnvelope, LifxEvent> {
    val lifxServer = LifxServer()

    onStart {
        lifxServer.start().collect {
            emit(it)
        }
    }

    process {
        lifxServer.sendCommand(it.payload, it.target)
    }
}

data class NetworkCommandEnvelope(
    val target: InetAddress = InetAddress.getByName("255.255.255.255"),
    val payload: LifxCommand
)
