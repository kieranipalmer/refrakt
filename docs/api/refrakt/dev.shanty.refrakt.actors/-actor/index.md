//[refrakt](../../../index.md)/[dev.shanty.refrakt.actors](../index.md)/[Actor](index.md)

# Actor

[jvm]\
interface [Actor](index.md)&lt;[TIN](index.md), [TOUT](index.md)&gt; : CoroutineScope

## Functions

| Name | Summary |
|---|---|
| [emit](emit.md) | [jvm]<br>abstract suspend fun [emit](emit.md)(it: [TOUT](index.md)) |
| [sendTo](send-to.md) | [jvm]<br>abstract suspend fun [sendTo](send-to.md)(it: [TIN](index.md)) |

## Properties

| Name | Summary |
|---|---|
| [coroutineContext](../-actor-impl/index.md#-1654120400%2FProperties%2F-1216412040) | [jvm]<br>abstract val [coroutineContext](../-actor-impl/index.md#-1654120400%2FProperties%2F-1216412040): [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html) |
| [outbox](outbox.md) | [jvm]<br>abstract val [outbox](outbox.md): Flow&lt;[TOUT](index.md)&gt; |

## Inheritors

| Name |
|---|
| [ActorImpl](../-actor-impl/index.md) |
