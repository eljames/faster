package org.faster.token;

import java.util.Scanner;

public class LtDefault implements LineToken {
	
	private final Scanner scanner;

	public LtDefault(Scanner scanner) {
		scanner.useDelimiter("\\n");
		this.scanner = scanner;
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
