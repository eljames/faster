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
		int data = input.read();
		this.size--;
		return data;
	}
	
	@Override
	public int read(byte[] bytes) throws IOException {
		if(this.size == 0)
			return -1;
		int buffer = bytes.length;
		if(buffer > this.size) {
			return extract(bytes, (int)this.size);
		}
		return extract(bytes, buffer);
	}
	
	private int extract(byte[] bytes, int buffer) throws IOException {
		int bytesRead = this.input.read(bytes, 0, buffer);
		this.size = this.size - bytesRead;
		return bytesRead;
	}

}
