package org.faster.token;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class LtDefault implements LineToken {
	
	private final InputStream input;

	public LtDefault(final InputStream in) {
		this.input = in;
	}

	@Override
	public String next() throws IOException {
		ByteArrayOutputStream write = new ByteArrayOutputStream();
		char ch = '\0';
		int data = -1;
		while((data = this.input.read()) != -1) {
			ch = (char)data;
			if(ch == '\n') break;
			write.write(ch);
		}
		return new String(write.toByteArray(), StandardCharsets.UTF_8);
	}
}
