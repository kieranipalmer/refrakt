package dev.shanty.lifx.messages

import dev.shanty.lifx.readNullTerminatedStringWithMaxLength
import dev.shanty.lifx.skip
import java.net.InetAddress
import java.nio.ByteBuffer

sealed interface LifxEvent {

    val source: InetAddress

    data class Acknowledgement(override val source: InetAddress) : LifxEvent

    data class StateService(
        override val source: InetAddress,
        val service: UByte,
        val port: Int,
    ) : LifxEvent {
        companion object {
            fun decodeFromBuffer(source: InetAddress, buffer: ByteBuffer): StateService {
                return StateService(
                    source,
                    buffer.get().toUByte(),
                    buffer.int,
                )
            }
        }
    }

    data class LightState(
        override val source: InetAddress,
        val hue: UShort,
        val saturation: UShort,
        val brightness: UShort,
        val kelvin: UShort,
        val power: Boolean,
        val label: String,
    ) : LifxEvent {
        companion object {
            fun decodeFromBuffer(source: InetAddress, buffer: ByteBuffer): LightState {
                val hue = buffer.short.toUShort()
                val saturation = buffer.short.toUShort()
                val brightness = buffer.short.toUShort()
                val kelvin = buffer.short.toUShort()
                buffer.skip(2)
                val power = buffer.short.toUShort() > 0u
                val label = buffer.readNullTerminatedStringWithMaxLength(32)
                buffer.skip(8)

                return LightState(
                    source,
                    hue,
                    saturation,
                    brightness,
                    kelvin,
                    power,
                    label
                )
            }
        }
    }
}
