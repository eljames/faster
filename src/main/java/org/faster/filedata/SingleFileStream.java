package org.faster.filedata;

import java.io.IOException;
import java.io.InputStream;

public class SingleFileStream extends InputStream {
	
	private InputStream input;
	private long size;
	
	public SingleFileStream(final InputStream in, final long size) {
		this.input = in;
		this.size = size;
	}

	@Override
	public int read() throws IOException {
		if(this.size == 0)
			return -1;
		int read = this.input.read();
		this.size--;
		return read;
	}

}
