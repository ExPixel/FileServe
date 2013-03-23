package com.fileserve.net.packets;

import java.nio.file.Path;


public class FileTransferStartPacket {
	public Path file;
	public int id;

	public FileTransferStartPacket(Path file, int id) {
		this.file = file;
		this.id = id;
	}
}
