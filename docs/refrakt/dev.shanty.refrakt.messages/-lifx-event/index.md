//[refrakt](../../../index.md)/[dev.shanty.refrakt.messages](../index.md)/[LifxEvent](index.md)

# LifxEvent

[jvm]\
interface [LifxEvent](index.md)

## Types

| Name | Summary |
|---|---|
| [Acknowledgement](-acknowledgement/index.md) | [jvm]<br>data class [Acknowledgement](-acknowledgement/index.md)(val source: [InetAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html)) : [LifxEvent](index.md) |
| [LightState](-light-state/index.md) | [jvm]<br>data class [LightState](-light-state/index.md)(val source: [InetAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html), val hue: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), val saturation: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), val brightness: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), val kelvin: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), val power: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [LifxEvent](index.md) |
| [StateService](-state-service/index.md) | [jvm]<br>data class [StateService](-state-service/index.md)(val source: [InetAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html), val service: [UByte](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-byte/index.html), val port: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [LifxEvent](index.md) |

## Properties

| Name | Summary |
|---|---|
| [source](source.md) | [jvm]<br>abstract val [source](source.md): [InetAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html) |

## Inheritors

| Name |
|---|
| [Acknowledgement](-acknowledgement/index.md) |
| [StateService](-state-service/index.md) |
| [LightState](-light-state/index.md) |
