package org.faster.filedata;

import java.io.IOException;
import java.io.InputStream;

public interface CopiedData {
	
	/**
	 * Copies data from a {@link InputStream} object.
	 * @param input
	 * @throws IOException 
	 */
	void copy(InputStream input) throws IOException;

}
