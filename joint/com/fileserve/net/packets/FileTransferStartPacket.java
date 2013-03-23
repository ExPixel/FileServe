package com.fileserve.net.packets;

import java.nio.file.Path;


public class FileTransferStartPacket {
	/**
	 * The path of the file being sent.
	 */
	public Path file;

	/**
	 * The ID of the file being sent.
	 */
	public int id;

	/**
	 * The size of the file being sent.
	 */
	public long length;

	public FileTransferStartPacket(Path file, int id) {
		this.file = file;
		this.id = id;
	}
}
