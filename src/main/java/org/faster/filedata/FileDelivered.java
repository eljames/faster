package org.faster.filedata;

import java.io.IOException;
import java.io.InputStream;

import org.faster.pathinfo.PathInfo;

public interface FileDelivered {
	/**
	 * This method must consume all {@code input} parameter until it ends the stream (until method {@code InputStream.get()} returns -1).
	 * If the {@code input} is not consumed, it will be thrown a exception after this method is executed.
	 * @param input
	 * @param info
	 * @throws IOException 
	 */
	void delivery(InputStream input, PathInfo info) throws IOException;
}
