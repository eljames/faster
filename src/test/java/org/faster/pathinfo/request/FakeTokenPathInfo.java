package org.faster.pathinfo.request;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.faster.token.DfTokenPathInfo;
import org.faster.token.TokenPathInfo;

public class FakeTokenPathInfo implements TokenPathInfo {
	
	private final TokenPathInfo token;
	
	public FakeTokenPathInfo(String stream) {
		this.token =
			new DfTokenPathInfo(
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
