package org.faster.responsedpath;

import java.io.IOException;
import java.nio.file.Path;

/**
 * @author Eli James
 */
public interface ResponsedPath {
	
	/**
	 * Responde with path's collection according to the requested path.
	 * @param relativePath
	 */

	void respond(Path relativePath) throws IOException;

}
