package org.faster.responsepaths;

import java.io.IOException;
import org.faster.dirmap.DirMap;
import org.faster.written.Written;

/**
 * Checks for the directory existence. If directory exists, it will be delegated by decorator.
 * @author Eli James
 *
 */
public class RspDirectoryExists implements ResponsePaths {
	
	private final ResponsePaths response;
	private final DirMap dirMap;
	private final Written written;
	
	private static final String ERROR =  "err";
	
	public RspDirectoryExists(final ResponsePaths response, final DirMap map, final Written written) {
		
		this.response = response;
		this.dirMap = map;
		this.written = written;
	}

	/**
	 * Checks for the directory existence. If directory exists, it will be delegated by decorator.
	 */
	@Override
	public void respond(CharSequence relativePath) throws IOException {
		
		// If it's not directory, returns error to the requester.
		if( !this.dirMap.has(relativePath)) {
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
			.writeLine()
			.flush();
	}

}
