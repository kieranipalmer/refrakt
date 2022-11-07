package dev.shanty.lifx.actors

import dev.shanty.lifx.LifxServer
import dev.shanty.lifx.messages.LifxCommand
import dev.shanty.lifx.messages.LifxEvent
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
