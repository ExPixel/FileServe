package com.fileserve.net.packets;


public class FileTransferStartPacket {
	public String file;
	public int id;

	public FileTransferStartPacket(String file, int id) {
		this.file = file;
		this.id = id;
	}
}
