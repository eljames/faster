package org.faster.pathinfo.request;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.PathInfo;
import org.faster.token.LineToken;

/**
 * Decorator, for file size.
 * @author Eli James Aguiar
 *
 */
public class PtSize implements PathInfo {
	
	private final long size;
	private final PathInfo path;

	public PtSize(PathInfo pathInfo, LineToken tokenPathInfo) throws IOException, ProtocolSyntaxErrorException {
		this(pathInfo, tokenPathInfo, false);
	}
	
	public PtSize(PathInfo pathInfo, LineToken tokenPathInfo, boolean directorySize) throws IOException, ProtocolSyntaxErrorException {
		this.size = parseSize(pathInfo, tokenPathInfo, directorySize);
		this.path = pathInfo;
	}

	@Override
	public String path() throws IOException {
		return this.path.path().toString();
	}

	@Override
	public long size() {
		return this.size;
	}

	@Override
	public boolean isDirectory() throws IOException {
		return this.path.isDirectory();
	}
	
	private long parseSize(PathInfo pathInfo, LineToken tokenPathInfo, boolean directorySize) throws ProtocolSyntaxErrorException, IOException {
		
		// If it's a directory, there's no size to show.
		if(pathInfo.isDirectory()) {
			if(!directorySize) {
				return -1; 
			}
		}
		
		String sizeStream = tokenPathInfo.next();
		
		try {
			return Long.parseLong(sizeStream);
		} catch(NumberFormatException ex) {
			throw new ProtocolSyntaxErrorException("The '" + sizeStream + "' is not a valid size. The size must be a number.");
		}
	}

}
