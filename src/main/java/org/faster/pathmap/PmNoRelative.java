package org.faster.pathmap;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import org.faster.virtualpath.VirtualPath;

public class PmNoRelative implements PathMap {
	
	private final PathMap origin;
	
	public PmNoRelative(final PathMap map) {
		this.origin = map;
	}
	
	/**
	 * If has some double period (relative path), return false. Must not allow relative path instructions.
	 */
	@Override
	public boolean has(final CharSequence path) {
		
		if(hasRelative(path)) {
			return false;
		}
		return this.origin.has(path);
	}

	@Override
	public VirtualPath get(final CharSequence path) throws FileNotFoundException {
		if(hasRelative(path)) {
			throw new FileNotFoundException("The path must not have relative instructions, like double period. Eg: /documents/../");
		}
		return this.origin.get(path);
	}
	
	private boolean hasRelative(final CharSequence path) {
		Iterator<Path> pathIt = Paths.get(path.toString()).iterator();
		while(pathIt.hasNext()) {
			if(pathIt.next().toString().equals("..")) { 
				return true;
			}
		}
		return false;
	}
}
