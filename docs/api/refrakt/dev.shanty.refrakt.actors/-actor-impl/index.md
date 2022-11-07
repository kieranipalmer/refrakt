//[refrakt](../../../index.md)/[dev.shanty.refrakt.actors](../index.md)/[ActorImpl](index.md)

# ActorImpl

[jvm]\
class [ActorImpl](index.md)&lt;[TIN](index.md), [TOUT](index.md)&gt;(context: [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html)) : [Actor](../-actor/index.md)&lt;[TIN](index.md), [TOUT](index.md)&gt; , CoroutineScope

## Constructors

| | |
|---|---|
| [ActorImpl](-actor-impl.md) | [jvm]<br>fun [ActorImpl](-actor-impl.md)(context: [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [emit](emit.md) | [jvm]<br>open suspend override fun [emit](emit.md)(it: [TOUT](index.md)) |
| [sendTo](send-to.md) | [jvm]<br>open suspend override fun [sendTo](send-to.md)(it: [TIN](index.md)) |

## Properties

| Name | Summary |
|---|---|
| [coroutineContext](index.md#-1654120400%2FProperties%2F-1216412040) | [jvm]<br>open override val [coroutineContext](index.md#-1654120400%2FProperties%2F-1216412040): [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html) |
| [inbox](inbox.md) | [jvm]<br>val [inbox](inbox.md): Channel&lt;[TIN](index.md)&gt; |
| [outbox](outbox.md) | [jvm]<br>open override val [outbox](outbox.md): SharedFlow&lt;[TOUT](index.md)&gt; |
