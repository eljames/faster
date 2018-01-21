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
		
		if( ! Files.isDirectory(root.resolve(relativePath.toString()))) {
			write();
			return;
		}
		
		this.response.respond(relativePath);
	}
	
	private void write() throws IOException {
		
		this.written
			.write(ERROR)
			.writeLine()
			.write("dnf") // Directory Not found or it's a regular file.
			.flush();
	}

}
