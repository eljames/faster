package org.faster.pathinfo.response;

import java.io.IOException;
import org.faster.pathinfo.PathInfo;
import org.faster.virtualpath.VirtualPath;

public class PtResponse implements PathInfo {
	
	private final VirtualPath virtual;
	
	public PtResponse(final VirtualPath virtual) {
		this.virtual = virtual;
	}

	@Override
	public boolean isDirectory() throws IOException {
		return !this.virtual.isDirectory();
	}

	@Override
	public CharSequence path() {
		return this.virtual.path();
	}

	@Override
	public long size() throws IOException {
		if(isDirectory()) return -1;
		return this.virtual.real().length();
	}

}
