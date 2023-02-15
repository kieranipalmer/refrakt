package dev.shanty.refrakt

import dev.shanty.refrakt.messages.LifxCommand
import dev.shanty.refrakt.messages.LifxEvent
import dev.shanty.refrakt.messages.decodeLifxHeader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.NetworkInterface
import java.nio.ByteBuffer
import java.nio.ByteOrder

class LifxServer {

    private val udpListeningSocket = DatagramSocket(56700)

    private val macAddresses = NetworkInterface.getNetworkInterfaces().toList().mapNotNull {
        it.hardwareAddress?.toUByteArray()?.let { MacAddress(it) }
    }

    init {
        logger.debug("Known Mac Addresses $macAddresses")
    }

    suspend fun sendCommand(
        command: LifxCommand,
        target: InetAddress = InetAddress.getByName("255.255.255.255")
    ) = withContext(Dispatchers.IO) {
        val bytes = command.serialise()
        val packet = DatagramPacket(bytes, bytes.size, target, 56700)
        udpListeningSocket.send(packet)
    }

    fun start(): Flow<LifxEvent> = flow {
        val receiveData = ByteArray(1024)

        while (true) {
            val receivedPacket = DatagramPacket(receiveData, receiveData.size)
            udpListeningSocket.receive(receivedPacket)
            val event = receivedPacket.decodeLifxMessage()
            if (event != null) {
                logger.debug { "Received $event" }
                emit(event)
            }
        }
    }.flowOn(Dispatchers.IO)

    private fun DatagramPacket.decodeLifxMessage(): LifxEvent? {
        val buffer = ByteBuffer.wrap(data)
            .order(ByteOrder.LITTLE_ENDIAN)

        val header = buffer.decodeLifxHeader()
        logger.debug("Received $header from $address:$port")

        val received = when (header.type.toUInt()) {
            3u -> LifxEvent.StateService.decodeFromBuffer(address, buffer)
            45u -> LifxEvent.Acknowledgement(address)
            107u -> LifxEvent.LightState.decodeFromBuffer(address, buffer)
            else -> null
        }

        return received
    }

    companion object {
        val logger = KotlinLogging.logger {}
    }
}
