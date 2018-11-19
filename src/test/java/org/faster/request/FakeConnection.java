package org.faster.request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.faster.connection.Connection;

public class FakeConnection implements Connection {
	
	private final OutputStream out;
	private final InputStream in;
	
	

	public FakeConnection(OutputStream out, InputStream in) {
		this.out = out;
		this.in = in;
	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public OutputStream output() throws IOException {
		return this.out;
	}

	@Override
	public InputStream input() throws IOException {
		return this.in;
	}

	@Override
	public Socket socket() throws IOException {
		throw new UnsupportedOperationException();
	}

}
