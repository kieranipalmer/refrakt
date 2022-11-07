package dev.shanty.refrakt.actors

import dev.shanty.refrakt.messages.LifxEvent

internal fun ActorManager.startDeviceManagerActor(
    discoveryActor: Actor<*, LifxEvent.StateService>,
    networkActor: Actor<NetworkCommandEnvelope, LifxEvent>
) = actor(discoveryActor.outbox) {

    process {
        val newActor = startDeviceActor(it.source, networkActor)
        emit(newActor)
    }
}
