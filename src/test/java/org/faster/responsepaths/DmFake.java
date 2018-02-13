package org.faster.responsepaths;

import java.util.Collection;

import org.faster.dirmap.DirMap;
import org.faster.pathinfo.PathInfo;

public class DmFake implements DirMap {
	
	private final boolean dir;
	
	public DmFake(final boolean dir) {
		this.dir = dir;
	}

	@Override
	public boolean has(String path) {
		return dir;
	}

	@Override
	public Collection<PathInfo> paths(String path) {
		return null;
	}

}
