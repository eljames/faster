package org.faster.token;

import java.util.Scanner;

public class DfTokenPathInfo implements TokenPathInfo {
	
	private final Scanner scanner;

	public DfTokenPathInfo(Scanner scanner) {
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
