package org.faster.dirmap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import org.faster.pathinfo.PathInfo;
import org.faster.pathinfo.response.PtResponse;
import org.faster.pathmap.PathMap;
import org.faster.virtualpath.VirtualPath;

public class DmDefault implements DirMap {
	
	private final PathMap map;
	
	public DmDefault(final PathMap mp) {
		this.map = mp;
	}

	@Override
	public boolean has(CharSequence path) {
		try {
			return this.map.get(path.toString()).isDirectory();
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public Collection<PathInfo> paths(final CharSequence virtualDir) throws UnsupportedOperationException, IOException {
		
		if(!this.has(virtualDir)) {
			throw new FileNotFoundException("The directory '" + virtualDir + "' not exists.");
		}
		
		return pathInfos(virtualDir);
		
	}
	
	private Collection<PathInfo> pathInfos(final CharSequence virtualDir) throws UnsupportedOperationException, IOException {
		Collection<PathInfo> paths = new ArrayList<>();
		for(VirtualPath virtual : this.map.get(virtualDir).paths()) {
			paths.add(new PtResponse(virtual));
		}
		return paths;
	}
}

