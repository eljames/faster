package org.faster.server.uploads.creation;

import java.io.IOException;

import org.faster.feedback.transfer.TransferRate;
import org.faster.virtualpath.VirtualPath;

public interface TransferElement extends TransferFile {
	TransferFile file(VirtualPath path);
	/**
	 * Sends the download rate. The {@code rate} is in bytes per second.
	 * @param transferRate
	 */
	void rate(TransferRate transferRate);
	void change(VirtualPath path) throws IOException;
	void finishedTransference();
	
}
