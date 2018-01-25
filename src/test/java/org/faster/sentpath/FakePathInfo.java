package org.faster.sentpath;

import java.io.IOException;

import org.faster.pathinfo.PathInfo;

public class FakePathInfo implements PathInfo {
	
	final private boolean directory;
	final private String path;
	final private long size;

	public FakePathInfo(final boolean directory, final String path, final long size) {
		this.directory = directory;
		this.path = path;
		this.size = size;
	}

	@Override
	public boolean isDirectory() throws IOException {
		return this.directory;
	}

	@Override
	public CharSequence path() throws IOException {
		return this.path;
	}

	@Override
	public long size() throws IOException {
		return this.size;
	}

}
