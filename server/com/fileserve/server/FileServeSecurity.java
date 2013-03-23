package com.fileserve.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.TextEncryptor;

import com.esotericsoftware.kryonet.Connection;
import com.fileserve.FileServeConstants;
import com.fileserve.net.packets.AuthPacket;
import com.fileserve.net.packets.AuthResultPacket;
import com.fileserve.net.packets.RequestPacket;
import com.fileserve.util.FileServeLog;
import com.fileserve.util.FileServeUtils;

public class FileServeSecurity {

	/**
	 * List of connections that are authorized to make requests.
	 */
	private Set<Connection> authorizedConnections = new HashSet<>();

	private Map<Connection, AttemptSession> attempts = new HashMap<>();

	private TextEncryptor textEncryptor;



	/**
	 * Requests that a connection authorize itself.
	 * @param connection
	 */
	public void authConnection( Connection connection ) {
		AttemptSession attemptSession = this.getAttemptSession(connection);
		if(attemptSession.getAttempts() == 3) {
			//After 3 attempts, we shut this thing down.
			FileServeLog.infof("Closing connection %s after 3 failed attempts.",
					connection.getRemoteAddressTCP().toString());
			this.clearAttemptSession(connection);
			connection.close(); // Forcefully close the connection.
			return; // hmph
		}
		attemptSession.addAttempt();
		connection.sendTCP( new RequestPacket( FileServeConstants.REQUEST_AUTH ) );
	}


	/**
	 * Attempts to authorize a connection after being sent a password (int the form of an AuthPacket).
	 * @param connection
	 * @param packet
	 */
	public void connectionAttemptAuth(Connection connection, AuthPacket packet) {
		String spass = ServerPreferences.security.get("password", null);

		// In this case there was no password specified,
		// so we save ourselves the headache of autorization.
		if(spass == null || spass.length() < 1) {
			this.addAuthorizedConnection(connection);
			return;
		}

		String plainTextSPass = this.getTextEncryptor().decrypt(spass);
		String hashedSPass = FileServeUtils.sha256Hash(plainTextSPass);

		if(hashedSPass.equals(packet.password)) {
			this.addAuthorizedConnection( connection );
		} else {
			connection.sendTCP(new AuthResultPacket(false));
			this.authConnection( connection );
		}
	}

	/**
	 * Adds a connection to the list of authorized connections.
	 * @param connection
	 */
	private void addAuthorizedConnection(Connection connection) {
		this.authorizedConnections.add(connection);
		this.clearAttemptSession(connection);
		connection.sendTCP(new AuthResultPacket(true));
	}


	/**
	 * Removes a connection from the list of authorized connections.
	 * @param connection
	 */
	public void removeAuthorizedConnection(Connection connection) {
		this.authorizedConnections.remove(connection);
	}


	/**
	 * 
	 * @return Gets the encryptor for this security object.
	 */
	private TextEncryptor getTextEncryptor() {
		if(this.textEncryptor == null) {
			this.textEncryptor = new BasicTextEncryptor();
		}
		return this.textEncryptor;
	}

	/**
	 * 
	 * @param connection
	 * @return true if a connection is authorized to make requests.
	 */
	public boolean isAuthorized( Connection connection ) {
		return this.authorizedConnections.contains(connection);
	}


	/**
	 * Removes an attempt session associated with a connection
	 * if the session exists.
	 * @param connection
	 */
	private void clearAttemptSession(Connection connection) {
		if(this.attempts.containsKey(connection)) {
			this.attempts.remove(connection);
		}
	}


	/**
	 * Gets an attempt session for a connection.
	 * If a session does not exist, one is created.
	 * @param connection
	 * @return The attempt session associated with the connection.
	 */
	private AttemptSession getAttemptSession(Connection connection) {
		if(!this.attempts.containsKey(connection)) {
			this.attempts.put(connection, new AttemptSession());
		}
		return this.attempts.get(connection);
	}


	/**
	 * Keeps track of the attempts a user has made.
	 * 
	 * @author Adolph C.
	 *
	 */
	private class AttemptSession {
		int attempts = 0;

		public void addAttempt() {
			this.attempts ++;
		}

		public int getAttempts() {
			return this.attempts;
		}
	}
}
