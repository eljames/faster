package org.faster.filedata;

import java.io.IOException;
import java.io.InputStream;

import org.faster.pathinfo.PathInfo;

public interface FileDelivered {
	/**
	 * If a single file will be downloaded, this method is called to handle how the file will be treat.
	 * The {@code input} parameter provide the file stream to be consumed.
	 * This method must consume all {@code input} parameter until it ends the stream (until method {@code InputStream.get()} returns -1).
	 * If the {@code input} is not totally consumed, it will be thrown a exception after this method is executed.
	 * @param input
	 * @param info
	 * @throws IOException 
	 */
	void file(InputStream input, PathInfo info) throws IOException;
	
	/**
	 * If a entire directory will be downloaded (its files and files of sub-directories), this method will be called with
	 * {@code PathInfo}. If the size is not supported because server don't provide it, the {@code PathInfo.size()} must be -1.
	 * If the server supports directory size, PathInfo must return the total size of files and sub-directories files.
	 * @param info
	 * @return HandledFile. This object will be called by HandledFile.handled() method for each file that belongs to the directory requested.
	 * @throws IOException 
	 */
	HandledFile directory(PathInfo info) throws IOException;
}
