//[refrakt](../../../../index.md)/[dev.shanty.refrakt.messages](../../index.md)/[LifxEvent](../index.md)/[LightState](index.md)

# LightState

[jvm]\
data class [LightState](index.md)(val source: [InetAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html), val hue: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), val saturation: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), val brightness: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), val kelvin: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), val power: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [LifxEvent](../index.md)

## Constructors

| | |
|---|---|
| [LightState](-light-state.md) | [jvm]<br>fun [LightState](-light-state.md)(source: [InetAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html), hue: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), saturation: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), brightness: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), kelvin: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), power: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), label: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [jvm]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [brightness](brightness.md) | [jvm]<br>val [brightness](brightness.md): [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html) |
| [hue](hue.md) | [jvm]<br>val [hue](hue.md): [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html) |
| [kelvin](kelvin.md) | [jvm]<br>val [kelvin](kelvin.md): [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html) |
| [label](label.md) | [jvm]<br>val [label](label.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [power](power.md) | [jvm]<br>val [power](power.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [saturation](saturation.md) | [jvm]<br>val [saturation](saturation.md): [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html) |
| [source](source.md) | [jvm]<br>open override val [source](source.md): [InetAddress](https://docs.oracle.com/javase/8/docs/api/java/net/InetAddress.html) |
