package org.faster.filedata;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;

public interface Download {
	/**
	 * Download files from server according to {@code path} parameter.
	 * @param path
	 * @throws IOException 
	 */
	void download(CharSequence path) throws ProtocolSyntaxErrorException, IOException;
}
