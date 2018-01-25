package org.faster.responsedpath;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author Eli James
 */
public interface ResponsedPath {
	
	/**
	 * Respond with path's collection according to the requested path. The path must be a directory.
	 * @param relativePath The relative path sent by requester. This path must be a directory.
	 */

	void respond(Path relativePath) throws IOException;

}
