package dev.shanty.lifx.actors

import dev.shanty.lifx.messages.LifxEvent

internal fun ActorManager.startDeviceManagerActor(
    discoveryActor: Actor<*, LifxEvent.StateService>,
    networkActor: Actor<NetworkCommandEnvelope, LifxEvent>
) = actor(discoveryActor.outbox) {

    process {
        val newActor = startDeviceActor(it.source, networkActor)
        emit(newActor)
    }
}
