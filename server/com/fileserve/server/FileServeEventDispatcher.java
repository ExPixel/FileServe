package com.fileserve.server;

import java.io.File;
import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;

public class FileServeEventDispatcher {

	ArrayList<FileServeEventListener> listeners = new ArrayList<>();

	/**
	 * Fires the event for when a connection is made.
	 * @param connection The connection that was made.
	 */
	public void fireConnected(Connection connection) {
		for(FileServeEventListener listener : this.listeners)
			listener.connected(connection);
	}

	/**
	 * Fires the event for when a connection is closed.
	 * @param connection The connection that was closed.
	 */
	public void fireDisconnected(Connection connection) {
		for(FileServeEventListener listener : this.listeners)
			listener.disconnected(connection);
	}

	/**
	 * Fires the event for when a file transfer starts.
	 * @param file File being transferred.
	 * @param connection
	 */
	public void fireFileTransferStart(File file, Connection connection) {
		for(FileServeEventListener listener : this.listeners)
			listener.fileTransferStart(file, connection);
	}

	/**
	 * Fires the event for when a file transfer ends.
	 * @param file
	 * @param connection
	 */
	public void fireFileTransferEnd(File file, Connection connection) {
		for(FileServeEventListener listener : this.listeners)
			listener.fileTransferEnd(file, connection);
	}

	public void addListener(FileServeEventListener listener) {
		this.listeners.add(listener);
	}

	public void removeListener(FileServeEventListener listener) {
		this.listeners.remove(listener);
	}
}
