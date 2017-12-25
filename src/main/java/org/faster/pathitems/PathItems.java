package org.faster.pathitems;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.PathInfo;

/**
 * Iteration on all files and directories from requested directory. Sub-directories not included.
 * @author Eli James Aguiar
 *
 */
public interface PathItems {
	
	/**
	 * Move to the next path item.
	 * @return true, if it has next item, false otherwise.
	 * @throws IOException
	 * @throws ProtocolSyntaxErrorException
	 */
	boolean next() throws IOException, ProtocolSyntaxErrorException;
	
	/**
	 * Get the current path.
	 * @return
	 */
	PathInfo pathInfo();

}
