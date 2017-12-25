package org.faster.request;

import java.io.IOException;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathitems.PathItems;

/**
 * Request files and folders requested peer.
 * @author Eli James Aguiar
 *
 */
public interface RequestedPaths {
	
	PathItems request(CharSequence path) throws IOException, ProtocolSyntaxErrorException;

}
