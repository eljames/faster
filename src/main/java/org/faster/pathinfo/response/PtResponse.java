package org.faster.pathinfo.response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.faster.pathinfo.PathInfo;

public class PtResponse implements PathInfo {
	
	private final Path rootPath;
	private final Path filePath;
	
	public PtResponse(Path rootPath, Path filePath) {
		this.rootPath = rootPath;
		this.filePath = filePath;
	}

	@Override
	public boolean isDirectory() {
		return Files.isDirectory(filePath);
	}

	@Override
	public CharSequence path() {
		return this.rootPath.getParent().relativize(this.filePath).toString();
	}

	@Override
	public long size() throws IOException {
		if(Files.isDirectory(this.filePath)) return -1;
		return Files.size(this.filePath);
	}

}
