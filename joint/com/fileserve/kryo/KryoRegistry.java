package com.fileserve.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.fileserve.net.packets.FileDataConfirmationPacket;
import com.fileserve.net.packets.FileDataPacket;
import com.fileserve.net.packets.FileTransferEndPacket;
import com.fileserve.net.packets.FileTransferRequestPacket;
import com.fileserve.net.packets.FileTransferResponsePacket;
import com.fileserve.net.packets.FileTransferStartPacket;

public class KryoRegistry {
	public static void register(Kryo kryo) {

		// File Transfer Packets:
		kryo.register(FileDataConfirmationPacket.class);
		kryo.register(FileDataPacket.class);
		kryo.register(FileTransferEndPacket.class);
		kryo.register(FileTransferRequestPacket.class);
		kryo.register(FileTransferResponsePacket.class);
		kryo.register(FileTransferStartPacket.class);


	}
}
