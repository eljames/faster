package org.faster.responsefiles;

import java.io.IOException;
import java.io.OutputStream;

import org.faster.virtualpath.VirtualPath;

public interface CreatedSentData {
	
	/**
	 * 
	 * @param dir The directory that will be upload
	 * @param out 
	 * @param totalSize
	 * @return
	 * @throws IOException
	 */
	SentData directory(VirtualPath dir, OutputStream out) throws IOException;
	SentData file(VirtualPath dir, OutputStream out) throws IOException;
}
