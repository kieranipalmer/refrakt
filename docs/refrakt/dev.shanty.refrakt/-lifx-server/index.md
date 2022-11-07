//[refrakt](../../../index.md)/[dev.shanty.refrakt](../index.md)/[LifxServer](index.md)

# LifxServer

[jvm]\
class [LifxServer](index.md)

## Constructors

| | |
|---|---|
| [LifxServer](-lifx-server.md) | [jvm]<br>fun [LifxServer](-lifx-server.md)() |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [jvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [sendCommand](send-command.md) | [jvm]<br>suspend fun [sendCommand](send-command.md)(command: [LifxCommand](../../dev.shanty.refrakt.messages/-lifx-command/index.md), target: [InetAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html) = InetAddress.getByName(&quot;255.255.255.255&quot;)) |
| [start](start.md) | [jvm]<br>fun [start](start.md)(): Flow&lt;[LifxEvent](../../dev.shanty.refrakt.messages/-lifx-event/index.md)&gt; |
