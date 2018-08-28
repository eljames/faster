package org.faster.filedata;

import java.io.IOException;
import java.io.InputStream;

public class Stream {
	
	private final InputStream input;
	
	public Stream(final InputStream in) {
		this.input = in;
	}
	
	public String asString() throws IOException {
		StringBuilder builder = new StringBuilder();
		int read = 0;
		byte[] bytes = new byte[8*1024];
		while((read = this.input.read(bytes)) != -1) {
			builder.append(new String(bytes, 0, read));
		}
		return builder.toString();
	}

}
