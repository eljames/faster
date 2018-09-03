package org.faster.filedata;

import java.io.IOException;
import java.io.InputStream;

import org.faster.pathinfo.PathInfo;

public class HandledFileNotConsumed implements HandledFile {
	
	private final HandledFile origin;
	
	public HandledFileNotConsumed(final HandledFile hdl) {
		this.origin = hdl;
	}

	@Override
	public void handle(final InputStream input, final PathInfo info) throws IOException {
		this.origin.handle(input, info);
		if(input.read() != -1) {
			throw new IOException("The 'input' parameter must be all consumed before 'delivery' method finishes.");
		}
	}

}
