package dev.shanty.refrakt.utils

import java.nio.ByteBuffer

fun ByteBuffer.skip(num: Int) = position(position() + num)
fun ByteBuffer.readNullTerminatedStringWithMaxLength(size: Int): String {
    val destArray = ByteArray(size)
    get(destArray, 0, size)
    val stringEnd = destArray.indexOfFirst { it == 0.toByte() }
    return String(destArray, 0, stringEnd)
}
