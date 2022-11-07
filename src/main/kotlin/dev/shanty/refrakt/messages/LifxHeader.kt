package dev.shanty.refrakt.messages

import dev.shanty.refrakt.MacAddress
import dev.shanty.refrakt.utils.skip
import java.nio.ByteBuffer

data class LifxHeader(
    val size: UShort,
    val protocol: UShort,
    val addressable: Boolean,
    val tagged: Boolean,
    val origin: UByte,
    val source: UInt,
    val target: MacAddress,
    val resRequired: Boolean,
    val ackRequired: Boolean,
    val sequence: UByte,
    val type: UShort,
) {
    companion object {
        val SIZE_BYTES = (36u).toUShort()
    }
}

fun ByteBuffer.decodeLifxHeader(): LifxHeader {
    val size = this.short.toUShort()
    val protocolTop = get().toUInt()
    val otherByte = get().toUByte()

    val protocolBottom = (otherByte and 0b00001111u).toUInt()
    val protocol = ((protocolBottom shl 8) or protocolTop).toUShort()

    val addressable = (otherByte and 0b00010000u) > 0u
    val tagged = (otherByte and 0b00100000u) > 0u
    val origin = ((otherByte and 0b11000000u).toUInt() shr 7).toUByte()
    val source = int.toUInt()

    val target = ByteArray(8)
    get(target, 0, 8)

    skip(6)

    val flagsByte = get().toUInt()
    val resRequired = (flagsByte and 1u) > 0u
    val ackRequired = ((flagsByte shr 1) and 1u) > 0u
    val sequence = get().toUByte()

    skip(8)

    val type = short.toUShort()

    skip(2)

    return LifxHeader(
        size,
        protocol,
        addressable,
        tagged,
        origin,
        source,
        MacAddress(target.toUByteArray()),
        resRequired,
        ackRequired,
        sequence,
        type,
    )
}

fun LifxHeader.encodeToByteBuffer(buffer: ByteBuffer) {
    buffer.putShort(size.toShort())

    val protocolUpper = (protocol.toUInt() and 0xFFu).toUByte() // 10000000001
    buffer.put(protocolUpper.toByte())

    val protocolLower = (protocol.toUInt() shr 8) and 0xFFu
    val addressableFlagByte = (if (addressable) 1u else 0u) shl 4
    val taggedFlagByte = (if (tagged) 1u else 0u) shl 5
    val origin = (origin.toUInt() and 0b11u) shl 7

    val lowerByte = protocolLower or addressableFlagByte or taggedFlagByte or origin
    buffer.put(lowerByte.toByte())

    buffer.putInt(source.toInt())
    buffer.put(target.bytes.toByteArray())
    buffer.skip(6)

    val resRequiredByte = (if (resRequired) 1u else 0u)
    val ackRequiredByte = (if (ackRequired) 1u else 0u) shl 1
    val byte22 = resRequiredByte or ackRequiredByte
    buffer.put(byte22.toByte())

    buffer.put(sequence.toByte())
    buffer.skip(8)

    buffer.putShort(type.toShort())
    buffer.skip(2)
}
