package org.faster.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class DfConnection implements Connection {
	
	private final Socket socket;
	
	public DfConnection(String ip, int port) throws UnknownHostException, IOException {
		this.socket = new Socket(ip, port);
	}

	@Override
	public void close() throws Exception {
		this.socket.close();
	}

	@Override
	public OutputStream output() throws IOException {
		return this.socket.getOutputStream();
	}

	@Override
	public InputStream input() throws IOException {
		return this.socket.getInputStream();
	}
	
	

}
