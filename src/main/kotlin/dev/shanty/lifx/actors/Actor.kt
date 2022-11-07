package dev.shanty.lifx.actors

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

interface Actor<TIN, TOUT> : CoroutineScope {
    val outbox: Flow<TOUT>

    suspend fun sendTo(it: TIN)
    suspend fun emit(it: TOUT)
}

class ActorImpl <TIN, TOUT>(context: CoroutineContext) : Actor<TIN, TOUT>, CoroutineScope by CoroutineScope(context) {
    val inbox = Channel<TIN>(onBufferOverflow = BufferOverflow.SUSPEND)
    private val _outbox = Channel<TOUT>(onBufferOverflow = BufferOverflow.SUSPEND)
    override val outbox = _outbox.receiveAsFlow().shareIn(this, SharingStarted.Eagerly, 0)

    override suspend fun sendTo(it: TIN) = inbox.send(it)
    override suspend fun emit(it: TOUT) = _outbox.send(it)
}

typealias ActorFactory<TIN, TOUT> = ActorBuilder<TIN, TOUT>.(actor: Actor<TIN, TOUT>) -> Unit
typealias ActorProcessor<TIN, TOUT> = suspend Actor<TIN, TOUT>.(TIN) -> Unit
typealias ActorOnStart<TIN, TOUT> = suspend Actor<TIN, TOUT>.() -> Unit

class ActorBuilder<TIN, TOUT> {
    var processInbox: ActorProcessor<TIN, TOUT> = {}
    var onStart: ActorOnStart<TIN, TOUT> = {}

    fun onStart(f: ActorOnStart<TIN, TOUT>) {
        onStart = f
    }

    fun process(f: ActorProcessor<TIN, TOUT>) {
        processInbox = f
    }
}

private fun <TIN, TOUT> actorImpl(context: CoroutineContext = EmptyCoroutineContext, init: ActorFactory<TIN, TOUT>): ActorImpl<TIN, TOUT> {
    val actor = ActorImpl<TIN, TOUT>(context)
    val actorBuilder = ActorBuilder<TIN, TOUT>()
    actorBuilder.init(actor)

    actor.launch {
        launch {
            actorBuilder.onStart(actor)
        }

        actor.inbox.receiveAsFlow().collect {
            actorBuilder.processInbox(actor, it)
        }
    }
    return actor
}

fun <TIN, TOUT> actor(context: CoroutineContext = EmptyCoroutineContext, init: ActorFactory<TIN, TOUT>): Actor<TIN, TOUT> {
    return actorImpl(context, init)
}

fun <TIN, TOUT> actor(context: CoroutineContext = EmptyCoroutineContext, incomingFlow: Flow<TIN>, init: ActorFactory<TIN, TOUT>): Actor<TIN, TOUT> {
    val actor = actorImpl(context, init)
    actor.launch {
        incomingFlow.collectToChannel(actor.inbox)
    }
    return actor
}

suspend fun <T> Flow<T>.collectToChannel(channel: Channel<T>) = collect {
    channel.send(it)
}
