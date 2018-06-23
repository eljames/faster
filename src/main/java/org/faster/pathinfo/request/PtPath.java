package org.faster.pathinfo.request;

import java.io.IOException;

import org.faster.pathinfo.PathInfo;
import org.faster.token.LineToken;


/**
 * Decorator for actual path.
 * @author Eli James Aguiar
 *
 */
public class PtPath implements PathInfo {
	
	private final PathInfo pathInfo;
	private final String path;
	
	public PtPath(PathInfo pathInfo, LineToken token) throws IOException {
		this.path = token.next();
		this.pathInfo = pathInfo;
	}

	@Override
	public String path() {
		return this.path;
	}

	@Override
	public long size() throws IOException {
		return this.pathInfo.size();
	}

	@Override
	public boolean isDirectory() throws IOException {
		return this.pathInfo.isDirectory();
	}

}
