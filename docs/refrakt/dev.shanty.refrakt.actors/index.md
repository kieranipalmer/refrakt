//[refrakt](../../index.md)/[dev.shanty.refrakt.actors](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [Actor](-actor/index.md) | [jvm]<br>interface [Actor](-actor/index.md)&lt;[TIN](-actor/index.md), [TOUT](-actor/index.md)&gt; : CoroutineScope |
| [ActorBuilder](-actor-builder/index.md) | [jvm]<br>class [ActorBuilder](-actor-builder/index.md)&lt;[TIN](-actor-builder/index.md), [TOUT](-actor-builder/index.md)&gt; |
| [ActorFactory](index.md#-1613042116%2FClasslikes%2F-1216412040) | [jvm]<br>typealias [ActorFactory](index.md#-1613042116%2FClasslikes%2F-1216412040)&lt;[TIN](index.md#-1613042116%2FClasslikes%2F-1216412040), [TOUT](index.md#-1613042116%2FClasslikes%2F-1216412040)&gt; = [ActorBuilder](-actor-builder/index.md)&lt;[TIN](index.md#-1613042116%2FClasslikes%2F-1216412040), [TOUT](index.md#-1613042116%2FClasslikes%2F-1216412040)&gt;.(actor: [Actor](-actor/index.md)&lt;[TIN](index.md#-1613042116%2FClasslikes%2F-1216412040), [TOUT](index.md#-1613042116%2FClasslikes%2F-1216412040)&gt;) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [ActorImpl](-actor-impl/index.md) | [jvm]<br>class [ActorImpl](-actor-impl/index.md)&lt;[TIN](-actor-impl/index.md), [TOUT](-actor-impl/index.md)&gt;(context: [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html)) : [Actor](-actor/index.md)&lt;[TIN](-actor-impl/index.md), [TOUT](-actor-impl/index.md)&gt; , CoroutineScope |
| [ActorManager](-actor-manager/index.md) | [jvm]<br>class [ActorManager](-actor-manager/index.md)(parentScope: CoroutineScope) |
| [ActorOnStart](index.md#971478947%2FClasslikes%2F-1216412040) | [jvm]<br>typealias [ActorOnStart](index.md#971478947%2FClasslikes%2F-1216412040)&lt;[TIN](index.md#971478947%2FClasslikes%2F-1216412040), [TOUT](index.md#971478947%2FClasslikes%2F-1216412040)&gt; = suspend [Actor](-actor/index.md)&lt;[TIN](index.md#971478947%2FClasslikes%2F-1216412040), [TOUT](index.md#971478947%2FClasslikes%2F-1216412040)&gt;.() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [ActorProcessor](index.md#-838059404%2FClasslikes%2F-1216412040) | [jvm]<br>typealias [ActorProcessor](index.md#-838059404%2FClasslikes%2F-1216412040)&lt;[TIN](index.md#-838059404%2FClasslikes%2F-1216412040), [TOUT](index.md#-838059404%2FClasslikes%2F-1216412040)&gt; = suspend [Actor](-actor/index.md)&lt;[TIN](index.md#-838059404%2FClasslikes%2F-1216412040), [TOUT](index.md#-838059404%2FClasslikes%2F-1216412040)&gt;.([TIN](index.md#-838059404%2FClasslikes%2F-1216412040)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [LightActorInput](-light-actor-input/index.md) | [jvm]<br>interface [LightActorInput](-light-actor-input/index.md) |
| [LightActorState](-light-actor-state/index.md) | [jvm]<br>data class [LightActorState](-light-actor-state/index.md)(val colour: [HsbkColour](../dev.shanty.refrakt.models/-hsbk-colour/index.md), val power: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val updatedAt: [Instant](https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html)) |
| [NetworkCommandEnvelope](-network-command-envelope/index.md) | [jvm]<br>data class [NetworkCommandEnvelope](-network-command-envelope/index.md)(val target: [InetAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html) = InetAddress.getByName(&quot;255.255.255.255&quot;), val payload: [LifxCommand](../dev.shanty.refrakt.messages/-lifx-command/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [actor](actor.md) | [jvm]<br>fun &lt;[TIN](actor.md), [TOUT](actor.md)&gt; [actor](actor.md)(context: [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html) = EmptyCoroutineContext, init: [ActorFactory](index.md#-1613042116%2FClasslikes%2F-1216412040)&lt;[TIN](actor.md), [TOUT](actor.md)&gt;): [Actor](-actor/index.md)&lt;[TIN](actor.md), [TOUT](actor.md)&gt;<br>fun &lt;[TIN](actor.md), [TOUT](actor.md)&gt; [actor](actor.md)(context: [CoroutineContext](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-coroutine-context/index.html) = EmptyCoroutineContext, incomingFlow: Flow&lt;[TIN](actor.md)&gt;, init: [ActorFactory](index.md#-1613042116%2FClasslikes%2F-1216412040)&lt;[TIN](actor.md), [TOUT](actor.md)&gt;): [Actor](-actor/index.md)&lt;[TIN](actor.md), [TOUT](actor.md)&gt; |
| [collectToChannel](collect-to-channel.md) | [jvm]<br>suspend fun &lt;[T](collect-to-channel.md)&gt; Flow&lt;[T](collect-to-channel.md)&gt;.[collectToChannel](collect-to-channel.md)(channel: Channel&lt;[T](collect-to-channel.md)&gt;) |
