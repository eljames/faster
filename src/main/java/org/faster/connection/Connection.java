package org.faster.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Connection extends AutoCloseable {
	
	OutputStream output() throws IOException;
	InputStream input() throws IOException;

}
