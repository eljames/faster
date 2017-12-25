package org.faster.pathinfo;

public interface PathInfo {
	
	
	/**
	 * Returns true if directory, otherwise, a regular file.
	 * @return 
	 */
	boolean isDirectory();
	
	/**
	 * Returns a file path or directory path. The returned path is relative to the requested peer root '/'.
	 * @return
	 */
	CharSequence path();
	
	
	long size();
	
	/**
	 * A object that represents a unknown path.
	 */
	public static final PathInfo NO_PATH_INFO = new NoPathInfo();

}
