package dev.shanty.refrakt

import dev.shanty.refrakt.actors.ActorManager
import dev.shanty.refrakt.actors.startDeviceManagerActor
import dev.shanty.refrakt.actors.startLifxDiscoveryActor
import dev.shanty.refrakt.actors.startLifxNetworkActor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Facade around Lifx Actor
class Lifx internal constructor(actorManager: ActorManager) {

    constructor(scope: CoroutineScope) : this(ActorManager(scope))

    private val lifxNetworkActor = actorManager.startLifxNetworkActor()
    private val lifxDiscoveryActor = actorManager.startLifxDiscoveryActor(5000, lifxNetworkActor)
    private val deviceDiscoveryActor = actorManager.startDeviceManagerActor(lifxDiscoveryActor, lifxNetworkActor)

    val discoveryEvents: Flow<Device> = deviceDiscoveryActor.outbox.map {
        Device.Light(it)
    }
}
