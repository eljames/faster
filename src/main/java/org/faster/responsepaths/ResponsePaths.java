package org.faster.responsepaths;

import java.io.IOException;

/**
 * @author Eli James
 */
public interface ResponsePaths {
	
	/**
	 * Respond with path's collection according to the requested path. The path must be a directory.
	 * @param relativePath The relative path sent by requester. This path must be a directory.
	 */

	void respond(CharSequence relativePath) throws IOException;

}
