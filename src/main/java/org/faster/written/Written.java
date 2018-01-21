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
	 * Write charSeq only if b is true
	 * @param b 
	 * @param charSeq
	 * @return
	 * @throws IOException
	 */
	Written writeIf(boolean b, CharSequence charSeq) throws IOException;

	/**
	 * Write num only if b is true
	 * @param b 
	 * @param charSeq
	 * @return
	 * @throws IOException
	 */
	Written writeIf(boolean b, long num) throws IOException;
	Written writeLine() throws IOException;
	void flush() throws IOException;

}