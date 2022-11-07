package dev.shanty.refrakt.actors

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class ActorManager(parentScope: CoroutineScope) {
    private val actorManagerJob = SupervisorJob(parentScope.coroutineContext[Job])
    private val actorCoroutineContext: CoroutineContext = parentScope.coroutineContext + actorManagerJob

    private val knownActors = mutableMapOf<String, Actor<*, *>>()

    fun <TIN, TOUT> uniqueActor(key: String, init: ActorFactory<TIN, TOUT>): Actor<TIN, TOUT> {
        return knownActors.getOrPut(key) {
            actor(init)
        } as Actor<TIN, TOUT>
    }

    fun <TIN, TOUT> uniqueActor(key: String, inFlow: Flow<TIN>, init: ActorFactory<TIN, TOUT>): Actor<TIN, TOUT> {
        val found = knownActors.getOrPut(key) {
            actor<TIN, TOUT>(inFlow, init)
        }

        return found as Actor<TIN, TOUT>
    }

    fun <TIN, TOUT> actor(init: ActorFactory<TIN, TOUT>) =
        actor<TIN, TOUT>(actorCoroutineContext, init)
    fun <TIN, TOUT> actor(inFlow: Flow<TIN>, init: ActorFactory<TIN, TOUT>) =
        actor<TIN, TOUT>(actorCoroutineContext, inFlow, init)
}
