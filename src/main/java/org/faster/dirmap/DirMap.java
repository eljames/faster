package org.faster.dirmap;

import java.util.Collection;

import org.faster.pathinfo.PathInfo;

/**
 * Maps virtual paths to real paths.
 * @author Eli James
 *
 */
public interface DirMap {
	
	/**
	 * Returns {@code true} if exists the virtual {@code path}.
	 * @param path
	 * @return true if directory exists and it's not a regular file, false, otherwise.
	 */
	boolean has(String path);
	
	/**
	 * Returns all virtual paths referred to the given {@code path}
	 * @param path
	 * @return
	 */
	Collection<PathInfo> paths(String path);

}
