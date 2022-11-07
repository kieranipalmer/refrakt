package dev.shanty.refrakt.messages

import dev.shanty.refrakt.MacAddress
import dev.shanty.refrakt.models.HsbkColour
import dev.shanty.refrakt.skip
import java.nio.ByteBuffer
import java.nio.ByteOrder

sealed interface LifxCommand : LifxSerializable

interface LifxSerializable {
    fun serialise(): ByteArray
}

object GetService : LifxCommand {

    override fun serialise(): ByteArray {
        val header = LifxHeader(
            size = LifxHeader.SIZE_BYTES,
            protocol = 1024u,
            addressable = true,
            tagged = false,
            origin = 0u,
            source = 0u,
            target = MacAddress.ZERO,
            resRequired = true,
            ackRequired = false,
            sequence = 0u,
            type = 2u,
        )

        val buffer = ByteBuffer.allocate(header.size.toInt()).order(ByteOrder.LITTLE_ENDIAN)
        header.encodeToByteBuffer(buffer)
        return buffer.array()
    }
}

object GetColour : LifxCommand {
    override fun serialise(): ByteArray {
        val size = LifxHeader.SIZE_BYTES.toInt()
        val buffer = ByteBuffer.allocate(size).order(ByteOrder.LITTLE_ENDIAN)
        val header = LifxHeader(
            size = size.toUShort(),
            protocol = 1024u,
            addressable = true,
            tagged = false,
            origin = 0u,
            source = 0u,
            target = MacAddress.ZERO,
            resRequired = false,
            ackRequired = false,
            sequence = 0u,
            type = 101u
        )

        header.encodeToByteBuffer(buffer)
        return buffer.array()
    }
}

data class SetPower(
    val power: Boolean,
    val target: MacAddress = MacAddress.ZERO,
) : LifxCommand {

    override fun serialise(): ByteArray {
        val size = (LifxHeader.SIZE_BYTES + 2u).toInt()
        val buffer = ByteBuffer.allocate(size).order(ByteOrder.LITTLE_ENDIAN)
        val header = LifxHeader(
            size = size.toUShort(),
            protocol = 1024u,
            addressable = true,
            tagged = false,
            origin = 0u,
            source = 0u,
            target = target,
            resRequired = false,
            ackRequired = false,
            sequence = 0u,
            type = 21u
        )

        header.encodeToByteBuffer(buffer)
        val value = if (power) 65535u else 0u
        buffer.putShort(value.toShort())

        return buffer.array()
    }
}

data class SetColour(
    val colour: HsbkColour,
    val duration: UInt,
    val target: MacAddress = MacAddress.ZERO,
) : LifxCommand {

    override fun serialise(): ByteArray {
        val size = (LifxHeader.SIZE_BYTES + 13u).toInt()
        val buffer = ByteBuffer.allocate(size).order(ByteOrder.LITTLE_ENDIAN)

        val header = LifxHeader(
            size = size.toUShort(),
            protocol = 1024u,
            addressable = true,
            tagged = false,
            origin = 0u,
            source = 0u,
            target = target,
            resRequired = false,
            ackRequired = false,
            sequence = 0u,
            type = 102u
        )

        header.encodeToByteBuffer(buffer)
        buffer.skip(1)
        buffer.putShort(colour.hue.toShort())
        buffer.putShort(colour.saturation.toShort())
        buffer.putShort(colour.brightness.toShort())
        buffer.putShort(colour.kelvin.toShort())
        buffer.putInt(duration.toInt())

        return buffer.array()
    }
}
