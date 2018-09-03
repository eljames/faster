package org.faster.filedata;

import java.io.IOException;
import java.io.InputStream;

import org.faster.pathinfo.PathInfo;

/**
 * Validates if the {@code InputStream input} parameter was all consumed by FileDelivered origin.
 * @author Eli James
 *
 */
public class FileDeliveredNotConsumed implements FileDelivered {
	
	private final FileDelivered origin;
	
	public FileDeliveredNotConsumed(final FileDelivered deliv) {
		this.origin = deliv;
	}

	@Override
	public void delivery(InputStream input, PathInfo info) throws IOException {
		this.origin.delivery(input, info);
		if(input.read() != -1) {
			throw new IOException("The 'input' parameter must be all consumed before 'delivery' method finishes.");
		}
	}

	@Override
	public HandledFile directory(PathInfo info) throws IOException {
		return this.origin.directory(info);
	}

}
