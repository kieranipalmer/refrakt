//[refrakt](../../index.md)/[dev.shanty.refrakt.messages](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [GetColour](-get-colour/index.md) | [jvm]<br>object [GetColour](-get-colour/index.md) : [LifxCommand](-lifx-command/index.md) |
| [GetService](-get-service/index.md) | [jvm]<br>object [GetService](-get-service/index.md) : [LifxCommand](-lifx-command/index.md) |
| [LifxCommand](-lifx-command/index.md) | [jvm]<br>interface [LifxCommand](-lifx-command/index.md) : [LifxSerializable](-lifx-serializable/index.md) |
| [LifxEvent](-lifx-event/index.md) | [jvm]<br>interface [LifxEvent](-lifx-event/index.md) |
| [LifxHeader](-lifx-header/index.md) | [jvm]<br>data class [LifxHeader](-lifx-header/index.md)(val size: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), val protocol: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html), val addressable: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val tagged: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val origin: [UByte](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-byte/index.html), val source: [UInt](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-int/index.html), val target: [MacAddress](../dev.shanty.refrakt/-mac-address/index.md), val resRequired: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val ackRequired: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val sequence: [UByte](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-byte/index.html), val type: [UShort](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-short/index.html)) |
| [LifxSerializable](-lifx-serializable/index.md) | [jvm]<br>interface [LifxSerializable](-lifx-serializable/index.md) |
| [SetColour](-set-colour/index.md) | [jvm]<br>data class [SetColour](-set-colour/index.md)(val colour: [HsbkColour](../dev.shanty.refrakt.models/-hsbk-colour/index.md), val duration: [UInt](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-u-int/index.html), val target: [MacAddress](../dev.shanty.refrakt/-mac-address/index.md) = MacAddress.ZERO) : [LifxCommand](-lifx-command/index.md) |
| [SetPower](-set-power/index.md) | [jvm]<br>data class [SetPower](-set-power/index.md)(val power: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val target: [MacAddress](../dev.shanty.refrakt/-mac-address/index.md) = MacAddress.ZERO) : [LifxCommand](-lifx-command/index.md) |

## Functions

| Name | Summary |
|---|---|
| [decodeLifxHeader](decode-lifx-header.md) | [jvm]<br>fun [ByteBuffer](https://docs.oracle.com/javase/8/docs/api/java/nio/ByteBuffer.html).[decodeLifxHeader](decode-lifx-header.md)(): [LifxHeader](-lifx-header/index.md) |
| [encodeToByteBuffer](encode-to-byte-buffer.md) | [jvm]<br>fun [LifxHeader](-lifx-header/index.md).[encodeToByteBuffer](encode-to-byte-buffer.md)(buffer: [ByteBuffer](https://docs.oracle.com/javase/8/docs/api/java/nio/ByteBuffer.html)) |
