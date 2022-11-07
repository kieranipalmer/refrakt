//[refrakt](../../../index.md)/[dev.shanty.refrakt.messages](../index.md)/[SetPower](index.md)

# SetPower

[jvm]\
data class [SetPower](index.md)(val power: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val target: [MacAddress](../../dev.shanty.refrakt/-mac-address/index.md) = MacAddress.ZERO) : [LifxCommand](../-lifx-command/index.md)

## Constructors

| | |
|---|---|
| [SetPower](-set-power.md) | [jvm]<br>fun [SetPower](-set-power.md)(power: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), target: [MacAddress](../../dev.shanty.refrakt/-mac-address/index.md) = MacAddress.ZERO) |

## Functions

| Name | Summary |
|---|---|
| [serialise](serialise.md) | [jvm]<br>open override fun [serialise](serialise.md)(): [ByteArray](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html) |

## Properties

| Name | Summary |
|---|---|
| [power](power.md) | [jvm]<br>val [power](power.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [target](target.md) | [jvm]<br>val [target](target.md): [MacAddress](../../dev.shanty.refrakt/-mac-address/index.md) |
