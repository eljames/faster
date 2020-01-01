package org.faster.requestlistened;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.faster.exception.ProtocolSyntaxErrorException;

public class RlMultiUser implements RequestListened {
	
	private final ConnectedHost host;
	private final ServerSocket server;
	
	public RlMultiUser(final ConnectedHost connhost, ServerSocket serv) {
		this.host = connhost;
		this.server = serv;
		
	}

	@Override
	public void start() throws IOException, ProtocolSyntaxErrorException {
		while(true) {
			Socket socket = this.server.accept();
			this.host.connected(socket);
		}
	}
}
