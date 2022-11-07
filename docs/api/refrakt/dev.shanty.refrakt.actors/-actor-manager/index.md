//[refrakt](../../../index.md)/[dev.shanty.refrakt.actors](../index.md)/[ActorManager](index.md)

# ActorManager

[jvm]\
class [ActorManager](index.md)(parentScope: CoroutineScope)

## Constructors

| | |
|---|---|
| [ActorManager](-actor-manager.md) | [jvm]<br>fun [ActorManager](-actor-manager.md)(parentScope: CoroutineScope) |

## Functions

| Name | Summary |
|---|---|
| [actor](actor.md) | [jvm]<br>fun &lt;[TIN](actor.md), [TOUT](actor.md)&gt; [actor](actor.md)(init: [ActorFactory](../index.md#-1613042116%2FClasslikes%2F-1216412040)&lt;[TIN](actor.md), [TOUT](actor.md)&gt;): [Actor](../-actor/index.md)&lt;[TIN](actor.md), [TOUT](actor.md)&gt;<br>fun &lt;[TIN](actor.md), [TOUT](actor.md)&gt; [actor](actor.md)(inFlow: Flow&lt;[TIN](actor.md)&gt;, init: [ActorFactory](../index.md#-1613042116%2FClasslikes%2F-1216412040)&lt;[TIN](actor.md), [TOUT](actor.md)&gt;): [Actor](../-actor/index.md)&lt;[TIN](actor.md), [TOUT](actor.md)&gt; |
| [uniqueActor](unique-actor.md) | [jvm]<br>fun &lt;[TIN](unique-actor.md), [TOUT](unique-actor.md)&gt; [uniqueActor](unique-actor.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), init: [ActorFactory](../index.md#-1613042116%2FClasslikes%2F-1216412040)&lt;[TIN](unique-actor.md), [TOUT](unique-actor.md)&gt;): [Actor](../-actor/index.md)&lt;[TIN](unique-actor.md), [TOUT](unique-actor.md)&gt;<br>fun &lt;[TIN](unique-actor.md), [TOUT](unique-actor.md)&gt; [uniqueActor](unique-actor.md)(key: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), inFlow: Flow&lt;[TIN](unique-actor.md)&gt;, init: [ActorFactory](../index.md#-1613042116%2FClasslikes%2F-1216412040)&lt;[TIN](unique-actor.md), [TOUT](unique-actor.md)&gt;): [Actor](../-actor/index.md)&lt;[TIN](unique-actor.md), [TOUT](unique-actor.md)&gt; |
