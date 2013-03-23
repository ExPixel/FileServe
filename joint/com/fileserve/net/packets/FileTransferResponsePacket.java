package com.fileserve.net.packets;

public class FileTransferResponsePacket {
	public boolean failed = true;

	public FileTransferResponsePacket(boolean failed) {
		super();
		this.failed = failed;
	}

}
