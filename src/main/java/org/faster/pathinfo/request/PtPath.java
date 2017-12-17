package org.faster.pathinfo.request;

import java.io.IOException;

import org.faster.token.TokenPathInfo;


/**
 * Decorator for actual path.
 * @author Eli James Aguiar
 *
 */
public class PtPath implements PathInfo {
	
	private final PathInfo pathInfo;
	private final String path;
	
	public PtPath(PathInfo pathInfo, TokenPathInfo token) throws IOException {
		this.path = token.next();
		this.pathInfo = pathInfo;
	}

	@Override
	public String path() {
		return this.path;
	}

	@Override
	public long size() {
		return this.pathInfo.size();
	}

	@Override
	public boolean isDirectory() {
		return this.pathInfo.isDirectory();
	}

}
