package dev.shanty.refrakt.actors

import dev.shanty.akt.actor.Actor
import dev.shanty.akt.actor.actor
import dev.shanty.refrakt.messages.LifxEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

internal fun CoroutineScope.startDeviceManagerActor(
    discoveryActor: Actor<*, LifxEvent.StateService>,
    networkActor: Actor<NetworkCommandEnvelope, LifxEvent>
): Actor<LifxEvent.StateService, Actor<LightActorInput, LightActorState>> {

    val actor = actor<LifxEvent.StateService, Actor<LightActorInput, LightActorState>> {
        val newActor = startLightActor(it.source, networkActor)
        emit(newActor)
    }

    actor.launch {
        discoveryActor.outbox.onEach {
            actor.sendTo(it)
        }.collect()
    }

    return actor
}

