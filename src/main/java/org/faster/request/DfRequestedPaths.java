package org.faster.request;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathitems.PathItems;
import org.faster.token.DfTokenPathInfo;

public class DfRequestedPaths implements RequestedPaths {
	
	
	private final Socket socket;
	private final RequestedPaths requested;
	
	public DfRequestedPaths(final String ipAddress, final int port) throws UnknownHostException, IOException {
		
		this.socket = new Socket(ipAddress, port);
		
		this.requested = new RqRequestedPaths(
			new RqResponsedPaths(
				new DfTokenPathInfo(
					new Scanner(
						this.socket.getInputStream()
					)
				)
			),
			this.socket.getOutputStream()
		);
		
	}

	@Override
	public PathItems request(CharSequence path) throws IOException, ProtocolSyntaxErrorException {
		return this.requested.request(path);
	}

	@Override
	public void close() throws IOException {
		this.requested.close();
		this.socket.close();
	}
}
