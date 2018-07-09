package org.faster.token;

import java.io.IOException;
import java.io.InputStream;

public class LtDefault implements LineToken {
	
	private final InputStream input;

	public LtDefault(final InputStream in) {
		this.input = in;
	}

	@Override
	public String next() throws IOException {
		StringBuilder sb = new StringBuilder();
		char ch = '\0';
		int data = -1;
		while((data = this.input.read()) != -1) {
			ch = (char)data;
			if(ch == '\n') break;
			sb.append(ch);
		}
		return sb.toString();
	}
}
