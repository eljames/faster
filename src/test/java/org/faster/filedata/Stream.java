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
		int i = 0;
		while((i = this.input.read()) != -1) {
			builder.append((char)i);
		}
		return builder.toString();
	}

}
