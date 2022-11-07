//[refrakt](../../index.md)/[dev.shanty.refrakt.actors](index.md)/[actor](actor.md)

# actor

[jvm]\
fun &lt;[TIN](actor.md), [TOUT](actor.md)&gt; [actor](actor.md)(context: [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html) = EmptyCoroutineContext, init: [ActorFactory](index.md#-1613042116%2FClasslikes%2F-1216412040)&lt;[TIN](actor.md), [TOUT](actor.md)&gt;): [Actor](-actor/index.md)&lt;[TIN](actor.md), [TOUT](actor.md)&gt;

fun &lt;[TIN](actor.md), [TOUT](actor.md)&gt; [actor](actor.md)(context: [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html) = EmptyCoroutineContext, incomingFlow: Flow&lt;[TIN](actor.md)&gt;, init: [ActorFactory](index.md#-1613042116%2FClasslikes%2F-1216412040)&lt;[TIN](actor.md), [TOUT](actor.md)&gt;): [Actor](-actor/index.md)&lt;[TIN](actor.md), [TOUT](actor.md)&gt;
