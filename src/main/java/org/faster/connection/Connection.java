package org.faster.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface Connection extends AutoCloseable {
	
	OutputStream output() throws IOException;
	InputStream input() throws IOException;
	Socket socket() throws IOException;

}
