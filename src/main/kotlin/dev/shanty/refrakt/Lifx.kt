package dev.shanty.refrakt

import dev.shanty.refrakt.actors.startDeviceManagerActor
import dev.shanty.refrakt.actors.startLifxDiscoveryActor
import dev.shanty.refrakt.actors.startLifxNetworkActor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Facade around Lifx Actor
class Lifx(scope: CoroutineScope) {

    private val lifxNetworkActor = scope.startLifxNetworkActor()
    private val lifxDiscoveryActor = scope.startLifxDiscoveryActor(5000, lifxNetworkActor)
    private val deviceDiscoveryActor = scope.startDeviceManagerActor(lifxDiscoveryActor, lifxNetworkActor)

    val discoveryEvents: Flow<Device> = deviceDiscoveryActor.outbox.map {
        Device.Light(it)
    }
}
