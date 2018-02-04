package org.faster.responsedpath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.faster.written.Written;

/**
 * Checks for the directory existence. If directory exists, it will be delegated by decorator.
 * @author Eli James
 *
 */
public class RspDirectoryExists implements ResponsedPath {
	
	private final ResponsedPath response;
	private final Path root;
	private final Written written;
	
	private static final String ERROR =  "err";
	
	public RspDirectoryExists(final ResponsedPath response, final Path rootPath, final Written written) {
		
		this.response = response;
		this.root = rootPath;
		this.written = written;
	}

	/**
	 * Checks for the directory existence. If directory exists, it will be delegated by decorator.
	 */
	@Override
	public void respond(Path relativePath) throws IOException {
		
		// If it's not directory, returns error to the requester.
		if( ! Files.isDirectory(root.resolve(relativePath.toString()))) {
			writeError();
			return;
		}
		
		// If file exists, it will delegated by a decorator.
		this.response.respond(relativePath);
	}
	
	/*
	 * This method writes that:
	 * --
	 * err
	 * dnf
	 * --
	 * Don't count the dashes.
	 * The responded peer must understand that the given path is not available to download files.
	 */
	private void writeError() throws IOException {
		
		this.written
			.write(ERROR)
			.writeLine()
			.write("dnf") // Directory Not found or it's a regular file.
			.flush();
	}

}
