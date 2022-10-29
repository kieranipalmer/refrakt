package dev.shanty.lifx.messages

import dev.shanty.lifx.MacAddress
import dev.shanty.lifx.models.HsbkColour
import dev.shanty.lifx.skip
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.crypto.Mac

sealed interface LifxCommand {

    object GetService : LifxCommand

}

interface LifxSerializable {
    fun serialise(): ByteArray
}

data class SetColour(
    val colour: HsbkColour,
    val duration: UInt,
    val target: MacAddress = MacAddress.ZERO,
) : LifxCommand, LifxSerializable {

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
