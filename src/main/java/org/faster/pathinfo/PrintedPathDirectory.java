package org.faster.pathinfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PrintedPathDirectory implements PrintedPath {
	
	private final PrintedPath origin;
	private final File directory;
	
	public PrintedPathDirectory(final PrintedPath printed, final File dir) {
		this.origin = printed;
		this.directory = dir;
	}

	@Override
	public String pathType() throws IOException {
		return this.origin.pathType();
	}

	@Override
	public String path() throws IOException {
		return this.origin.path();
	}

	@Override
	public String size() throws IOException {
		Path folder = this.directory.toPath();
	    long size = Files.walk(folder)
	      .filter(p -> p.toFile().isFile())
	      .mapToLong(p -> p.toFile().length())
	      .sum();
	    return String.valueOf(size);
	}

	@Override
	public String print() throws IOException {
		
		return new StringBuilder()
			.append(this.origin.print())
			.append("\n")
			.append(this.size())
			.toString();
	}
}
