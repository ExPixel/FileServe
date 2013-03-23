package com.fileserve.server;

import java.io.File;

import com.esotericsoftware.kryonet.Connection;

public class FileServeEvents {

	/**
	 * Fires the event for when a connection is made.
	 * @param connection The connection that was made.
	 */
	public void fireConnected(Connection connection) {

	}

	/**
	 * Fires the event for when a connection is closed.
	 * @param connection The connection that was closed.
	 */
	public void fireDisconnected(Connection connection) {

	}

	/**
	 * Fires the event for when a file transfer starts.
	 * @param file File being transferred.
	 * @param connection
	 */
	public void fireFileTransferStart(File file, Connection connection) {

	}

	/**
	 * Fires the event for when a file transfer ends.
	 * @param file
	 * @param connection
	 */
	public void fireFileTransferEnd(File file, Connection connection) {

	}
}
