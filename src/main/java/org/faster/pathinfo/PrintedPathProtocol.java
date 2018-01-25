package org.faster.pathinfo;

import java.io.IOException;

public class PrintedPathProtocol implements PrintedPath {
	
	
	final private PathInfo path;
	
	public PrintedPathProtocol(final PathInfo pathInfo) {
		this.path = pathInfo;
	}

	@Override
	public String pathType() throws IOException {
		return this.path.isDirectory() ? "d" : "f";
	}

	@Override
	public String path() throws IOException {
		return this.path.path().toString();
	}

	@Override
	public String size() throws IOException {
		return this.path.isDirectory() ? "" : String.valueOf(this.path.size());
	}

}
