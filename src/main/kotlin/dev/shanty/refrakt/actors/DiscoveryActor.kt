package dev.shanty.refrakt.actors

import dev.shanty.refrakt.messages.LifxCommand
import dev.shanty.refrakt.messages.LifxEvent
import dev.shanty.refrakt.utils.timeoutAfter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

internal fun ActorManager.startLifxDiscoveryActor(
    discoveryTime: Long,
    networkActor: Actor<NetworkCommandEnvelope, LifxEvent>
) = actor {
    val knownDevices = mutableSetOf<LifxEvent.StateService>()

    onStart {
        launch {
            while (isActive) {
                sendTo(Unit)
                delay(30000)
            }
        }
    }

    process {
        println("Running Discovery")

        networkActor.sendTo(NetworkCommandEnvelope(payload = LifxCommand.GetService))
        val discoveryResponses = networkActor.outbox.mapNotNull {
            it as? LifxEvent.StateService
        }.timeoutAfter(discoveryTime).toList()

        val newDevices = discoveryResponses
            .distinctBy { it.source }
            .filterNot {
                knownDevices.contains(it)
            }

        newDevices.forEach {
            knownDevices.add(it)
            emit(it)
        }
    }
}
