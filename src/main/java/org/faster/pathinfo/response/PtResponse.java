package org.faster.pathinfo.response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.faster.pathinfo.PathInfo;

public class PtResponse implements PathInfo {
	
	private final Path rootPath;
	private final File file;
	
	public PtResponse(final Path rootPath, final File filePath) {
		this.rootPath = rootPath;
		this.file = filePath;
	}

	@Override
	public boolean isDirectory() {
		return this.file.isDirectory();
	}

	@Override
	public CharSequence path() {
		return "/" + this.rootPath
				.relativize(this.file.toPath())
				.toString();
	}

	@Override
	public long size() throws IOException {
		if(isDirectory()) return -1;
		return this.file.length();
	}

}
