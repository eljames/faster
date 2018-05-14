package org.faster.pathmap;

import java.io.FileNotFoundException;
import org.faster.virtualpath.VirtualPath;

/**
 * 
 * @author Eli James
 *
 */
public interface PathMap {
	
	/**
	 * @param path is the virtual path mapped on a {@code PathMap} instance.
	 * @return Returns the real path according to the given {@code path} parameter.
	 * @throws FileNotFoundException if there is no file that corresponds the given {@path}
	 */
	VirtualPath get(final CharSequence path) throws FileNotFoundException;
	
	/**
	 * @param path is the virtual path mapped on a {@code PathMap} instance.
	 * @return Returns {@code true} if there is a real path mapped to virual {@code path}
	 */
	boolean has(final CharSequence path);

}
