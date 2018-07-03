package org.faster.token;

import java.util.Scanner;

public class LtDefault implements LineToken {
	
	private final Scanner scanner;

	public LtDefault(final Scanner scanner) {
		this.scanner = scanner;
		this.scanner.useDelimiter("\\n");
	}

	@Override
	public String next() {
		return this.scanner.next();
	}

	@Override
	public void close() {
		this.scanner.close();
	}

}
