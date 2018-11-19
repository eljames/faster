package org.faster.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CnCached implements Connection {
	
	private final Connection origin;
	private final InputStream input;
	private final OutputStream output;
	
	public CnCached(final Connection con, final InputStream in, final OutputStream out) {
		this.origin = con;
		this.input = in;
		this.output = out;
	}

	@Override
	public void close() throws Exception {
		this.origin.close();
	}

	@Override
	public OutputStream output() throws IOException {
		return this.output;
	}

	@Override
	public InputStream input() throws IOException {
		return this.input;
	}

	@Override
	public Socket socket() throws IOException {
		return this.origin.socket();
	}

}
