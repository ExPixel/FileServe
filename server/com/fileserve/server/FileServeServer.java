package com.fileserve.server;

import java.io.File;
import java.io.IOException;

import com.esotericsoftware.kryonet.Server;
import com.fileserve.kryo.KryoRegistry;

public class FileServeServer {
	protected Server server;
	private boolean running = false;
	private Thread serverStartThread;

	private FileServeEventDispatcher eventDispatcher = new FileServeEventDispatcher();
	private FileServeSecurity security = new FileServeSecurity();

	/**
	 * The top-most accessible directory in the file system.
	 */
	File topFile;

	/**
	 * Starts the server on a different thread.
	 */
	public void start() {

		// Stop the server thread if it is already running.
		// This should never really happen, but just in case.
		if(this.serverStartThread != null && this.serverStartThread.isAlive()) {
			this.serverStartThread.interrupt();
		}

		this.serverStartThread = new Thread(new Runnable() {
			@Override
			public void run() {
				FileServeServer.this.startServerThread();
			}
		}, "Starting Server");
		this.serverStartThread.start();
	}

	/**
	 * Starts the server on the current thread.
	 */
	private void startServerThread() {
		this.server = new Server();
		this.server.start();
		KryoRegistry.register(this.server.getKryo());
		try {
			this.server.bind( ServerPreferences.server.getInt("tcpPort", 1235) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setRunning(true);
	}

	/**
	 * Stops the server if it is running (if getRunning() returns true).
	 */
	public void stop() {
		if(this.server != null && this.isRunning()) {
			this.setRunning(false);
			this.server.stop();
		}
	}

	/**
	 * Sets whether or not the server is running.
	 * @param val
	 */
	private void setRunning(boolean val) {
		this.running = val;
	}

	/**
	 * 
	 * @return Whether or not the server is running.
	 */
	public boolean isRunning() {
		return this.running;
	}

	/**
	 * 
	 * @return The event dispatcher for this FileServeServer.
	 */
	public FileServeEventDispatcher getEventDispatcher() {
		return this.eventDispatcher;
	}

	/**
	 * Shorthand for getEventDispatcher().addListener(FileServeEventListener);
	 * 
	 * @param listener
	 * @see com.fileserve.server.FileServeEventDispatcher#addListener(com.fileserve.server.FileServeEventListener)
	 */
	public void addListener(FileServeEventListener listener) {
		this.eventDispatcher.addListener(listener);
	}

	/**
	 * Shorthand for getEventDispatcher().removeListener(FileServeEventListener);
	 * 
	 * @param listener
	 * @see com.fileserve.server.FileServeEventDispatcher#removeListener(com.fileserve.server.FileServeEventListener)
	 */
	public void removeListener(FileServeEventListener listener) {
		this.eventDispatcher.removeListener(listener);
	}

	/**
	 * @return the topFile
	 */
	public File getTopFile() {
		return this.topFile;
	}

	/**
	 * @param topFile the topFile to set
	 */
	public void setTopFile(File topFile) {
		this.topFile = topFile;
	}

	/**
	 * @return the server
	 */
	public Server getServer() {
		return this.server;
	}

	/**
	 * @return the security
	 */
	public FileServeSecurity getSecurity() {
		return this.security;
	}
}
