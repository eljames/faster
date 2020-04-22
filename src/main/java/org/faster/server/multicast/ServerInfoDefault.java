package org.faster.server.multicast;

public class ServerInfoDefault implements ServerInfo {

	private final String name;
	private final String ip;
	private final int port;
	
	public ServerInfoDefault(String name, String ip, int port) {
		this.name = name;
		this.ip = ip;
		this.port = port;
	}
	
	@Override
	public String name() {
		return this.name;
	}

	@Override
	public String ip() {
		return this.ip;
	}

	@Override
	public int port() {
		return this.port;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		ServerInfo server = (ServerInfo) obj;
		if(this.ip().equals(server.ip()) && this.port() == server.port())
			return true;
		return false;
	}
}
