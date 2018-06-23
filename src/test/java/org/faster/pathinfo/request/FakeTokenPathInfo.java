package org.faster.pathinfo.request;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.faster.token.LtDefault;
import org.faster.token.LineToken;

public class FakeTokenPathInfo implements LineToken {
	
	private final LineToken token;
	
	public FakeTokenPathInfo(String stream) {
		this.token =
			new LtDefault(
				new Scanner(
					new ByteArrayInputStream(
						stream.getBytes(StandardCharsets.UTF_8)
					)
				)
			);
	}

	@Override
	public String next() {
		return token.next();
	}

	@Override
	public void close() {
		this.token.close();
	}

}
