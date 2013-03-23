package com.fileserve.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class FileServeServerListener extends Listener{

	FileServeServer server;

	public FileServeServerListener(FileServeServer server) {
		this.server = server;
	}

	@Override
	public void connected(Connection connection) {
		this.server.getEventDispatcher().fireConnected(connection);
	}

	@Override
	public void disconnected(Connection connection) {
		this.server.getEventDispatcher().fireDisconnected(connection);
	}

}
