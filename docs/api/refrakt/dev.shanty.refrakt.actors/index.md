//[refrakt](../../index.md)/[dev.shanty.refrakt.actors](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [LightActorInput](-light-actor-input/index.md) | [jvm]<br>interface [LightActorInput](-light-actor-input/index.md) |
| [LightActorState](-light-actor-state/index.md) | [jvm]<br>data class [LightActorState](-light-actor-state/index.md)(val colour: [HsbkColour](../dev.shanty.refrakt.models/-hsbk-colour/index.md), val power: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val updatedAt: [Instant](https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html)) |
| [NetworkCommandEnvelope](-network-command-envelope/index.md) | [jvm]<br>data class [NetworkCommandEnvelope](-network-command-envelope/index.md)(val target: [InetAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html) = InetAddress.getByName(&quot;255.255.255.255&quot;), val payload: [LifxCommand](../dev.shanty.refrakt.messages/-lifx-command/index.md)) |
