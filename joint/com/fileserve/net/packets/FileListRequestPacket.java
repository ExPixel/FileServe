package com.fileserve.net.packets;

import java.nio.file.Path;

public class FileListRequestPacket {
	Path path;

	public FileListRequestPacket(Path path) {
		super();
		this.path = path;
	}
}
