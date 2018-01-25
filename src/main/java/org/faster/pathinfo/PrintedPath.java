package org.faster.pathinfo;

import java.io.IOException;

public interface PrintedPath {
	
	/**
	 * Describes if it's directory or regular file.
	 * @return
	 * @throws IOException 
	 */
	String pathType() throws IOException;
	
	/**
	 * Returns the path.
	 * @return path in a string form.
	 * @throws IOException 
	 */
	String path() throws IOException;
	
	/**
	 * Returns the size in a string form.
	 * @return size in a string form
	 * @throws IOException 
	 */
	String size() throws IOException;
	
	
	
}
