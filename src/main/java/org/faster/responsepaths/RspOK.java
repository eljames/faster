package org.faster.responsepaths;

import java.io.IOException;
import org.faster.written.Written;

public class RspOK implements ResponsePaths {
	
	private final ResponsePaths response;
	private final Written written;

	public RspOK(final ResponsePaths resp, final Written wrt) {
		this.response = resp;
		this.written = wrt;
	}

	/**
	 * Sends the a ok token to the responded peer.
	 */
	@Override
	public void respond(CharSequence relativePath) throws IOException {
		written.write("k").writeLine();
		response.respond(relativePath);
	}

}