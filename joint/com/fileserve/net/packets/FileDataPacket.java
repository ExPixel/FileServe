package com.fileserve.net.packets;


public class FileDataPacket {

	public byte[] data;
	public int id;

	public FileDataPacket() {}

	public FileDataPacket(int id, byte[] data) {
		this.id = id;
		this.data = data;
	}

}
