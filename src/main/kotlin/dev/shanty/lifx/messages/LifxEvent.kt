package dev.shanty.lifx.messages

import dev.shanty.lifx.readFixedLengthString
import java.nio.ByteBuffer

sealed interface LifxEvent {
    object Acknowledgement : LifxEvent

    data class StateService(
        val service: UByte,
        val port: Int,
    ) : LifxEvent {
        companion object {
            fun decodeFromBuffer(buffer: ByteBuffer): StateService {
                return StateService(
                    buffer.get().toUByte(),
                    buffer.int,
                )
            }
        }
    }

    data class LightState(
        val hue: UShort,
        val saturation: UShort,
        val brightness: UShort,
        val kelvin: UShort,
        val label: String,
    ) : LifxEvent {
        companion object {
            fun decodeFromBuffer(buffer: ByteBuffer): LightState {
                return LightState(
                    buffer.short.toUShort(),
                    buffer.short.toUShort(),
                    buffer.short.toUShort(),
                    buffer.short.toUShort(),
                    buffer.readFixedLengthString(32),
                )
            }
        }
    }
}