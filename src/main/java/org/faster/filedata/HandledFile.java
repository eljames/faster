package org.faster.filedata;

import java.io.IOException;
import java.io.InputStream;

import org.faster.pathinfo.PathInfo;

public interface HandledFile {
	void handle(InputStream input, PathInfo info) throws IOException;
	
	public static final HandledFile NOTHING = new HandledFileNothing();
	
	class HandledFileNothing implements HandledFile {

		@Override
		public void handle(InputStream input, PathInfo info) throws IOException {
			// Nothing
		}
		
	}
}
