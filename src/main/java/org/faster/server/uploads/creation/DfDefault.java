package org.faster.server.uploads.creation;

import org.faster.feedback.transfer.TransferedSize;

public class DfDefault implements TransferedFeedback {
	
	private final TransferedSize downloaded;
	
	public DfDefault(final TransferedSize ds) {
		this.downloaded = ds;
	}

	@Override
	public long size() {
		return downloaded.size();
	}

}
