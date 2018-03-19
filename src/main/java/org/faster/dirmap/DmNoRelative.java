package org.faster.dirmap;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import org.faster.pathinfo.PathInfo;

public class DmNoRelative implements DirMap {
	
	private final DirMap origin;
	
	public DmNoRelative(final DirMap map) {
		this.origin = map;
	}
	
	/**
	 * If has some double period (relative path), return false. Must not allow relative path instructions.
	 */
	@Override
	public boolean has(final CharSequence path) {
		
		Iterator<Path> pathIt = Paths.get(path.toString()).iterator();
		while(pathIt.hasNext()) {
			if(pathIt.next().toString().equals("..")) { 
				return false;
			}
		}
		return this.origin.has(path);
	}

	@Override
	public Collection<PathInfo> paths(final CharSequence path) {
		if(!this.has(path)) {
			throw new IllegalArgumentException("The path must not have relative instructions, like double period before slash. Eg: /documents/../");
		}
		return this.origin.paths(path);
	}

}
