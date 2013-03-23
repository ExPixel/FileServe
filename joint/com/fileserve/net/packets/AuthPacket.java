package com.fileserve.net.packets;

public class AuthPacket {
	/**
	 * SHA-256 hash of the password that is entered on the
	 * client side, using the hash provided by the server.
	 */
	public String password;
}
