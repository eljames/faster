package org.faster.virtualpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.faster.pathinfo.PathInfo;

public class VpPath implements VirtualPath {
	
	private final PathInfo info;
	
	public VpPath(final PathInfo path) {
		this.info = path;
	}

	@Override
	public CharSequence path() {
		try {
			return this.info.path();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public Collection<VirtualPath> paths() throws IOException {
		throw new UnsupportedOperationException();
	}

	@Override
	public File real() throws FileNotFoundException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isDirectory() {
		try {
			return this.info.isDirectory();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public long size() throws IOException {
		return this.info.size();
	}
	
}
