package org.faster.pathinfo.request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.faster.token.LtDefault;
import org.faster.token.LineToken;

public class FakeTokenPathInfo implements LineToken {
	
	private final LineToken token;
	
	public FakeTokenPathInfo(String stream) {
		this.token = new LtDefault(
			new ByteArrayInputStream(stream.getBytes(StandardCharsets.UTF_8))
		);
	}

	@Override
	public String next() throws IOException {
		return token.next();
	}

}
