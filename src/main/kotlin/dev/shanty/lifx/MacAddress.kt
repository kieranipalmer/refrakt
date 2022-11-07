package dev.shanty.lifx

import java.util.HexFormat

@JvmInline
value class MacAddress(val bytes: UByteArray) {
    override fun toString(): String {
        return bytes.asByteArray().toHexString(":")
    }

    companion object {
        val ZERO = MacAddress(UByteArray(8))
    }
}

fun UByte.toHex(): String = "%2x".format(this.toByte())
fun Byte.toHex(): String = "%2x".format(this)

fun UByteArray.toHexString(): String = toByteArray().toHexString()

fun ByteArray.toHexString(delimiter: String? = null): String = this.let {
    if (delimiter == null) {
        HexFormat.of().formatHex(it)
    } else {
        HexFormat.ofDelimiter(delimiter).formatHex(it)
    }
}
