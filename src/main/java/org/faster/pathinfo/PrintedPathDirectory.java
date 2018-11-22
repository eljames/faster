package org.faster.pathinfo;

import java.io.IOException;
import org.faster.virtualpath.VirtualPath;

public class PrintedPathDirectory implements PrintedPath {
	
	private final PrintedPath origin;
	private final VirtualPath directory;
	
	public PrintedPathDirectory(final PrintedPath printed, final VirtualPath dir) {
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
	    return String.valueOf(this.directory.size());
	}

	@Override
	public String print() throws IOException {
		
		return new StringBuilder()
			.append(this.origin.print())
			.append(this.size())
			.toString();
	}
}
