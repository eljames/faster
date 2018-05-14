package org.faster.dirmap;

import java.io.FileNotFoundException;
import java.io.IOException;
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
	 * @return true if the file exists and it's a directory, false, otherwise.
	 */
	boolean has(CharSequence path);
	
	/**
	 * Returns all virtual paths referred to the given directory {@code path}.
	 * @param path
	 * @return
	 * @throws FileNotFoundException if there is no director mapped.
	 * @throws IOException 
	 * @throws UnsupportedOperationException 
	 */
	Collection<PathInfo> paths(CharSequence path) throws UnsupportedOperationException, IOException;

}
