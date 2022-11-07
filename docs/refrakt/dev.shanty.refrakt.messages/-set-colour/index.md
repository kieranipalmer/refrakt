//[refrakt](../../../index.md)/[dev.shanty.refrakt.messages](../index.md)/[SetColour](index.md)

# SetColour

[jvm]\
data class [SetColour](index.md)(val colour: [HsbkColour](../../dev.shanty.refrakt.models/-hsbk-colour/index.md), val duration: [UInt](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-int/index.html), val target: [MacAddress](../../dev.shanty.refrakt/-mac-address/index.md) = MacAddress.ZERO) : [LifxCommand](../-lifx-command/index.md)

## Constructors

| | |
|---|---|
| [SetColour](-set-colour.md) | [jvm]<br>fun [SetColour](-set-colour.md)(colour: [HsbkColour](../../dev.shanty.refrakt.models/-hsbk-colour/index.md), duration: [UInt](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-int/index.html), target: [MacAddress](../../dev.shanty.refrakt/-mac-address/index.md) = MacAddress.ZERO) |

## Functions

| Name | Summary |
|---|---|
| [serialise](serialise.md) | [jvm]<br>open override fun [serialise](serialise.md)(): [ByteArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html) |

## Properties

| Name | Summary |
|---|---|
| [colour](colour.md) | [jvm]<br>val [colour](colour.md): [HsbkColour](../../dev.shanty.refrakt.models/-hsbk-colour/index.md) |
| [duration](duration.md) | [jvm]<br>val [duration](duration.md): [UInt](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-int/index.html) |
| [target](target.md) | [jvm]<br>val [target](target.md): [MacAddress](../../dev.shanty.refrakt/-mac-address/index.md) |
