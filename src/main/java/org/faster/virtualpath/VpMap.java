package org.faster.virtualpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

public class VpMap implements VirtualPath {
	
	final Map<String, Path> map;
	
	public VpMap(final Map<String, Path> mp) {
		this.map = mp;
	}

	@Override
	public CharSequence path() {
		return "/";
	}

	@Override
	public Collection<VirtualPath> paths() throws UnsupportedOperationException {
		
		final ArrayList<VirtualPath> virtualCollection = new ArrayList<VirtualPath>();
		for(Entry<String, Path> path : this.map.entrySet()) {
			virtualCollection.add(
				new VpDefault(
					Paths.get("/" + path.getKey()),
					path.getValue(),
					path.getValue().toFile()
				)
			);
		}
		return virtualCollection;
	}
	
	

	@Override
	public File real() throws FileNotFoundException {
		throw new FileNotFoundException();
	}

	@Override
	public boolean isDirectory() {
		return true;
	}

}
