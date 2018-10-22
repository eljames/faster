package org.faster.requestlistened;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RlMultiUser implements RequestListened {
	
	private final ConnectedHost host;
	private final ServerSocket server;
	
	public RlMultiUser(final ConnectedHost connhost, ServerSocket serv) {
		this.host = connhost;
		this.server = serv;
		
	}

	@Override
	public void start() throws IOException {
		while(true) {
			Socket socket = this.server.accept();
			this.host.connected(socket);
		}
	}
}
