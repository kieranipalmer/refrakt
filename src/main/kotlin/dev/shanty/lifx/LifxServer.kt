package dev.shanty.lifx

import dev.shanty.lifx.messages.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.NetworkInterface
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.random.Random
import kotlin.random.nextUInt

class LifxServer {

    private val udpListeningSocket = DatagramSocket(56700)

    private val macAddresses = NetworkInterface.getNetworkInterfaces().toList().mapNotNull {
        it.hardwareAddress?.toUByteArray()?.let { MacAddress(it) }
    }

    suspend fun sendCommand(command: LifxCommand) = withContext(Dispatchers.IO) {
        val source = Random.nextUInt()
        val bytes = when(command) {
            is LifxCommand.GetService -> {
                val header = LifxHeader(
                    size = LifxHeader.SIZE_BYTES,
                    protocol = 1024u,
                    addressable = true,
                    tagged = false,
                    origin = 0u,
                    source = source,
                    target = MacAddress.ZERO,
                    resRequired = true,
                    ackRequired = false,
                    sequence = 0u,
                    type = 2u,
                )

                val buffer = ByteBuffer.allocate(header.size.toInt()).order(ByteOrder.LITTLE_ENDIAN)
                header.encodeToByteBuffer(buffer)
                buffer.array()
            }

            is SetColour -> {
                command.serialise()
            }
        }

        println("Sending ${bytes.toHexString()}")

        val packet = DatagramPacket(bytes, bytes.size, InetAddress.getByName("255.255.255.255"), 56700)
        udpListeningSocket.send(packet)
    }

    suspend fun start(): Flow<LifxEvent> = flow {
        val receiveData = ByteArray(1024)

        println("Mac Addresses $macAddresses")

        while(true) {
            val recievedPacket = DatagramPacket(receiveData, receiveData.size)
            udpListeningSocket.receive(recievedPacket)
            val event = recievedPacket.decodeLifxMessage()
            if(event != null) {
                emit(event)
            }
        }
    }.flowOn(Dispatchers.IO)

    private fun DatagramPacket.decodeLifxMessage(): LifxEvent? {
        val buffer = ByteBuffer.wrap(data)
            .order(ByteOrder.LITTLE_ENDIAN)

        val header = buffer.decodeLifxHeader()
        println("Received $header from ${address}:${port}")

        return when(header.type.toUInt()) {
            3u -> LifxEvent.StateService.decodeFromBuffer(buffer)
            45u -> LifxEvent.Acknowledgement
            107u -> LifxEvent.LightState.decodeFromBuffer(buffer)
            else -> null
        }
    }
}

fun ByteBuffer.skip(num: Int) = position(position() + num)
fun ByteBuffer.readFixedLengthString(size: Int): String {
    val destArray = ByteArray(size)
    get(destArray, 0, size)
    return String(destArray)
}
