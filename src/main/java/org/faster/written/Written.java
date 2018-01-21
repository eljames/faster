package org.faster.written;

import java.io.IOException;

/**
 * Provide methods to write data stream (like socket stream) in a declarative way.
 * @author Eli James
 *
 */
public interface Written {
	
	
	Written write(CharSequence charSeq) throws IOException;
	Written write(long num) throws IOException;
	
	/**
	 *  Must write a Unix line ending "\n"
	 * @return
	 * @throws IOException
	 */
	Written writeLine() throws IOException;
	void flush() throws IOException;

}