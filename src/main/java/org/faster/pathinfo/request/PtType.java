package org.faster.pathinfo.request;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.PathInfo;
import org.faster.pathinfo.PathType;
import org.faster.token.TokenPathInfo;

/**
 * Decorator for path type.
 * @author Eli James Aguiar
 *
 */
public class PtType implements PathInfo {

	private final PathType pathType;
	
	private static final String FILE = "f";
	private static final String DIR = "d";
	
	public PtType(TokenPathInfo token) throws IOException, ProtocolSyntaxErrorException {
		this.pathType = objectPathType(token.next());
	}

	@Override
	public boolean isDirectory() {
		return this.pathType == PathType.DIRECTORY;
	}

	@Override
	public String path() {
		throw new UnsupportedOperationException("Method 'path' not supported by class '" + this.getClass().getSimpleName() + "'");
	}

	@Override
	public long size() {
		throw new UnsupportedOperationException("Method 'size' not supported by class '" + this.getClass().getSimpleName() + "'");
	}

	private PathType objectPathType(CharSequence pathTypeStream) throws ProtocolSyntaxErrorException {
		if(pathTypeStream.equals(FILE))
			return PathType.FILE;
		if(pathTypeStream.equals(DIR))
			return PathType.DIRECTORY;
		
		throw new ProtocolSyntaxErrorException("The file type '" + pathTypeStream + "' is unknown from protocol.");
	}

}
