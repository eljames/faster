package org.faster.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public final class CnDefault implements Connection {
	
	private final Socket socket;
	
	public CnDefault(final Socket socket) {
		this.socket = socket;
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