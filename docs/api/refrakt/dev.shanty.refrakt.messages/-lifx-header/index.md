//[refrakt](../../../index.md)/[dev.shanty.refrakt.messages](../index.md)/[LifxHeader](index.md)

# LifxHeader

[jvm]\
data class [LifxHeader](index.md)(val size: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), val protocol: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), val addressable: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val tagged: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val origin: [UByte](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-byte/index.html), val source: [UInt](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-int/index.html), val target: [MacAddress](../../dev.shanty.refrakt/-mac-address/index.md), val resRequired: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val ackRequired: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val sequence: [UByte](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-byte/index.html), val type: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html))

## Constructors

| | |
|---|---|
| [LifxHeader](-lifx-header.md) | [jvm]<br>fun [LifxHeader](-lifx-header.md)(size: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), protocol: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), addressable: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), tagged: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), origin: [UByte](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-byte/index.html), source: [UInt](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-int/index.html), target: [MacAddress](../../dev.shanty.refrakt/-mac-address/index.md), resRequired: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), ackRequired: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), sequence: [UByte](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-byte/index.html), type: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [jvm]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [ackRequired](ack-required.md) | [jvm]<br>val [ackRequired](ack-required.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [addressable](addressable.md) | [jvm]<br>val [addressable](addressable.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [origin](origin.md) | [jvm]<br>val [origin](origin.md): [UByte](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-byte/index.html) |
| [protocol](protocol.md) | [jvm]<br>val [protocol](protocol.md): [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html) |
| [resRequired](res-required.md) | [jvm]<br>val [resRequired](res-required.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [sequence](sequence.md) | [jvm]<br>val [sequence](sequence.md): [UByte](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-byte/index.html) |
| [size](size.md) | [jvm]<br>val [size](size.md): [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html) |
| [source](source.md) | [jvm]<br>val [source](source.md): [UInt](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-int/index.html) |
| [tagged](tagged.md) | [jvm]<br>val [tagged](tagged.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [target](target.md) | [jvm]<br>val [target](target.md): [MacAddress](../../dev.shanty.refrakt/-mac-address/index.md) |
| [type](type.md) | [jvm]<br>val [type](type.md): [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html) |

## Extensions

| Name | Summary |
|---|---|
| [encodeToByteBuffer](../encode-to-byte-buffer.md) | [jvm]<br>fun [LifxHeader](index.md).[encodeToByteBuffer](../encode-to-byte-buffer.md)(buffer: [ByteBuffer](https://docs.oracle.com/javase/8/docs/api/java/nio/ByteBuffer.html)) |
