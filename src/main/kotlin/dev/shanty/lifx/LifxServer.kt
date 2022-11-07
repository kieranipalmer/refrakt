package dev.shanty.lifx

import dev.shanty.lifx.messages.GetColour
import dev.shanty.lifx.messages.LifxCommand
import dev.shanty.lifx.messages.LifxEvent
import dev.shanty.lifx.messages.LifxHeader
import dev.shanty.lifx.messages.SetColour
import dev.shanty.lifx.messages.decodeLifxHeader
import dev.shanty.lifx.messages.encodeToByteBuffer
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

    suspend fun sendCommand(command: LifxCommand, target: InetAddress = InetAddress.getByName("255.255.255.255")) = withContext(Dispatchers.IO) {
        val source = Random.nextUInt()
        val bytes = when (command) {
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

            is GetColour -> {
                command.serialise()
            }
        }

        val packet = DatagramPacket(bytes, bytes.size, target, 56700)
        udpListeningSocket.send(packet)
    }

    fun start(): Flow<LifxEvent> = flow {
        val receiveData = ByteArray(1024)

        while (true) {
            val recievedPacket = DatagramPacket(receiveData, receiveData.size)
            udpListeningSocket.receive(recievedPacket)
            val event = recievedPacket.decodeLifxMessage()
            if (event != null) {
                emit(event)
            }
        }
    }.flowOn(Dispatchers.IO)

    private fun DatagramPacket.decodeLifxMessage(): LifxEvent? {
        val buffer = ByteBuffer.wrap(data)
            .order(ByteOrder.LITTLE_ENDIAN)

        val header = buffer.decodeLifxHeader()
//        println("Received $header from ${address}:${port}")

        val received = when (header.type.toUInt()) {
            3u -> LifxEvent.StateService.decodeFromBuffer(address, buffer)
            45u -> LifxEvent.Acknowledgement(address)
            107u -> LifxEvent.LightState.decodeFromBuffer(address, buffer)
            else -> null
        }

        return received
    }
}

fun ByteBuffer.skip(num: Int) = position(position() + num)
fun ByteBuffer.readNullTerminatedStringWithMaxLength(size: Int): String {
    val destArray = ByteArray(size)
    get(destArray, 0, size)
    val stringEnd = destArray.indexOfFirst { it == 0.toByte() }
    return String(destArray, 0, stringEnd)
}
