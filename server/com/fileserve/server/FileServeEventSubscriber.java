package com.fileserve.server;

import java.io.File;

import com.esotericsoftware.kryonet.Connection;

public interface FileServeEventSubscriber {
	/**
	 * Called when a connection is made.
	 * @param connection The connection that was made.
	 */
	public void connected(Connection connection);

	/**
	 * Fires when a connection is closed.
	 * @param connection The connection that was closed.
	 */
	public void disconnected(Connection connection);

	/**
	 * Fires when a file transfer begins.
	 * @param file The file being transferred.
	 * @param connection The connection that the file transfer is being sent through.
	 */
	public void fileTransferStart(File file, Connection connection);

	/**
	 * Fires when a file transfer is ended.
	 * @param file
	 * @param connection
	 */
	public void fileTransferEnd(File file, Connection connection);
}
