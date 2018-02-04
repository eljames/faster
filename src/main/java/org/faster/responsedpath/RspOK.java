package org.faster.responsedpath;

import java.io.IOException;
import java.nio.file.Path;

import org.faster.written.Written;

public class RspOK implements ResponsedPath {
	
	private final ResponsedPath response;
	private final Written written;

	public RspOK(final ResponsedPath resp, final Written wrt) {
		this.response = resp;
		this.written = wrt;
	}

	/**
	 * Sends the a ok token to the responded peer.
	 */
	@Override
	public void respond(Path relativePath) throws IOException {
		written.write("k");
		response.respond(relativePath);
	}

}