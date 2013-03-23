package com.fileserve.net.packets;

import java.nio.file.Path;

public class FileListResultPacket {
	Path[] paths;

	public FileListResultPacket(Path[] paths) {
		super();
		this.paths = paths;
	}

	public FileListResultPacket() {}

}
