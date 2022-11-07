//[refrakt](../../../index.md)/[dev.shanty.refrakt.actors](../index.md)/[NetworkCommandEnvelope](index.md)

# NetworkCommandEnvelope

[jvm]\
data class [NetworkCommandEnvelope](index.md)(val target: [InetAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html) = InetAddress.getByName(&quot;255.255.255.255&quot;), val payload: [LifxCommand](../../dev.shanty.refrakt.messages/-lifx-command/index.md))

## Constructors

| | |
|---|---|
| [NetworkCommandEnvelope](-network-command-envelope.md) | [jvm]<br>fun [NetworkCommandEnvelope](-network-command-envelope.md)(target: [InetAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html) = InetAddress.getByName(&quot;255.255.255.255&quot;), payload: [LifxCommand](../../dev.shanty.refrakt.messages/-lifx-command/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [payload](payload.md) | [jvm]<br>val [payload](payload.md): [LifxCommand](../../dev.shanty.refrakt.messages/-lifx-command/index.md) |
| [target](target.md) | [jvm]<br>val [target](target.md): [InetAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html) |
