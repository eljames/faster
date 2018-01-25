package org.faster.responsedpath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.faster.written.Written;

public class RspRequest implements ResponsedPath {
	
	private final ResponsedPath response;
	private final Path root;
	private final Written written;
	
	private static final String ERROR =  "err";
	
	public RspRequest(final ResponsedPath response, final Path rootPath, final Written written) {
		
		this.response = response;
		this.root = rootPath;
		this.written = written;
	}

	@Override
	public void respond(Path relativePath) throws IOException {
		
		// If it's not directory, returns error to the requester.
		if( ! Files.isDirectory(root.resolve(relativePath.toString()))) {
			writeError();
			return;
		}
		
		// If there's no problem with the given relative path, it will delegated by a decorator.
		this.response.respond(relativePath);
	}
	
	private void writeError() throws IOException {
		
		this.written
			.write(ERROR)
			.writeLine()
			.write("dnf") // Directory Not found or it's a regular file.
			.flush();
	}

}
