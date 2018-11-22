package org.faster.virtualpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.faster.pathinfo.PathInfo;

public interface VirtualPath {
	
	/**
	 * Get the virtual path on string format.
	 * @return
	 */
	CharSequence path();
	
	/**
	 * If this virual path is a directory, this returns all path that belongs to this directory.
	 * If this is not a directory, it will throw {@link UnsupportedOperationException}.
	 * Use {@link PathInfo#isDirectory()} from this {@link #path()} to check if it's a directory.
	 * @return
	 * @throws IOException 
	 */
	Collection<VirtualPath> paths() throws IOException;
	
	/**
	 * If this virtual path is representing a real file, it will return a physical file.
	 * E.g: C:\Users\john.
	 * If there is no real file mapped, it will throw {@code FileNotFoundException}.
	 * Use {@link #isDirectory()} to check the condition.
	 * @return the real path that is mapped to this virual path.
	 */
	File real() throws FileNotFoundException;
	
	/**
	 * Check if this virtual path is a directory
	 * @return true, if directory, false, otherwise;
	 */
	boolean isDirectory();
	
	/**
	 * Returns the size of this {@link VirtualPath}. It can return a directory size (recursive calculation) if this is a directory, or return a regular file size.
	 * If the size is not supported, it will return -1. 
	 * @return
	 * @throws IOException
	 */
	long size() throws IOException;
	

}
