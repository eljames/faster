package org.faster.responsefiles;

import java.io.IOException;

public interface ResponseFiles {
	/**
	 * Sends files to the requester according to given {@code path}.
	 * @param path
	 * @throws IOException 
	 */
	void send(CharSequence path) throws IOException;
}
