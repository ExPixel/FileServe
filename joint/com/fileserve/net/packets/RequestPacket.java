package com.fileserve.net.packets;

import com.fileserve.FileServeConstants;

public class RequestPacket {
	public int request_id = FileServeConstants.NO_REQUEST;
	public String value = null;

	public RequestPacket() {}

	public RequestPacket(int request_id, String value) {
		super();
		this.request_id = request_id;
		this.value = value;
	}

	public RequestPacket(int request_id) {
		super();
		this.request_id = request_id;
	}
}
