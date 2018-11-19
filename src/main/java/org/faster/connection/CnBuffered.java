package org.faster.connection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Buffered connection decorator
 * @author Eli James
 *
 */
public class CnBuffered implements Connection {
	
	private final Connection origin;
	
	public CnBuffered(final Connection con) {
		this.origin = con;
	}

	@Override
	public void close() throws Exception {
		this.origin.close();
	}

	@Override
	public OutputStream output() throws IOException {
		return new BufferedOutputStream(this.origin.output(), 8192);
	}

	@Override
	public InputStream input() throws IOException {
		return new BufferedInputStream(this.origin.input(), 8192);
	}

	@Override
	public Socket socket() throws IOException {
		return this.origin.socket();
	}

}
