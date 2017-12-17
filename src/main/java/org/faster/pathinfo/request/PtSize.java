package org.faster.pathinfo.request;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.token.TokenPathInfo;

/**
 * Decorator, for file size.
 * @author Eli James Aguiar
 *
 */
public class PtSize implements PathInfo {
	
	private final long size;
	private final PathInfo path;

	public PtSize(PathInfo pathInfo, TokenPathInfo tokenPathInfo) throws IOException, ProtocolSyntaxErrorException {
		
		this.size = parseSize(pathInfo, tokenPathInfo);
		this.path = pathInfo;

	}

	@Override
	public String path() {
		return this.path();
	}

	@Override
	public long size() {
		return this.size;
	}

	@Override
	public boolean isDirectory() {
		return this.path.isDirectory();
	}
	
	private long parseSize(PathInfo pathInfo, TokenPathInfo tokenPathInfo) throws ProtocolSyntaxErrorException {
		
		String sizeStream = tokenPathInfo.next();
		
		// If it's a directory, there's no size to show.
		if(pathInfo == PathType.DIRECTORY) {
			return -1; 
		}
		
		try {
			return Long.parseLong(sizeStream);
		} catch(NumberFormatException ex) {
			throw new ProtocolSyntaxErrorException("The '" + sizeStream + "' is not a valid size. The size must be a number.");
		}
	}

}
