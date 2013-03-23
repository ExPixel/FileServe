package com.fileserve.net.packets;

public class AuthResultPacket {
	public boolean success = false;

	public AuthResultPacket() {}

	public AuthResultPacket(boolean success) {
		super();
		this.success = success;
	}

}
