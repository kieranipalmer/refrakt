//[refrakt](../../../index.md)/[dev.shanty.refrakt.actors](../index.md)/[ActorManager](index.md)/[uniqueActor](unique-actor.md)

# uniqueActor

[jvm]\
fun &lt;[TIN](unique-actor.md), [TOUT](unique-actor.md)&gt; [uniqueActor](unique-actor.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), init: [ActorFactory](../index.md#-1613042116%2FClasslikes%2F-1216412040)&lt;[TIN](unique-actor.md), [TOUT](unique-actor.md)&gt;): [Actor](../-actor/index.md)&lt;[TIN](unique-actor.md), [TOUT](unique-actor.md)&gt;

fun &lt;[TIN](unique-actor.md), [TOUT](unique-actor.md)&gt; [uniqueActor](unique-actor.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), inFlow: Flow&lt;[TIN](unique-actor.md)&gt;, init: [ActorFactory](../index.md#-1613042116%2FClasslikes%2F-1216412040)&lt;[TIN](unique-actor.md), [TOUT](unique-actor.md)&gt;): [Actor](../-actor/index.md)&lt;[TIN](unique-actor.md), [TOUT](unique-actor.md)&gt;
