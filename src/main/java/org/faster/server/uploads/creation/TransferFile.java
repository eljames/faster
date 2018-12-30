package org.faster.server.uploads.creation;

import org.faster.feedback.transfer.TimeLeft;

public interface TransferFile {
	
	/**
	 * This method will be called each time the network brings data. This method will be stopped to be called
	 * when the total size of all files is reached.
	 * @param downloaded
	 */
	void downloaded(final DownloadedFeedback downloaded);
	
	/**
	 * Sends to {@link UploadElement} object the upload timeleft. The timeleft is in milliseconds.  
	 * @param timeleft
	 */
	void timeleft(TimeLeft timeleft);
	
}
