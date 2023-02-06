package dev.shanty.refrakt.actors

import dev.shanty.akt.actor.Actor
import dev.shanty.akt.actor.actor
import dev.shanty.refrakt.messages.GetService
import dev.shanty.refrakt.messages.LifxEvent
import dev.shanty.refrakt.utils.timeoutAfter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.slf4j.Logger
import org.slf4j.LoggerFactory

internal fun CoroutineScope.startLifxDiscoveryActor(
    discoveryTime: Long,
    networkActor: Actor<NetworkCommandEnvelope, LifxEvent>,
    logger: Logger = LoggerFactory.getLogger("LifxDiscoveryActor")
): Actor<Unit, LifxEvent.StateService> {

    val knownDevices = mutableSetOf<LifxEvent.StateService>()
    val actor = actor<Unit, LifxEvent.StateService> {
        logger.debug("Running Discovery")
        networkActor.sendTo(NetworkCommandEnvelope(payload = GetService))
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

    actor.launch {
        while (isActive) {
            actor.sendTo(Unit)
            delay(30000)
        }
    }

    return actor
}
