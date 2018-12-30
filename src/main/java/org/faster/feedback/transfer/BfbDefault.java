package org.faster.feedback.transfer;

import org.faster.feedback.BufferFeedback;

public class BfbDefault implements BufferFeedback {
	
	private final TotalUploaded uploaded;
	private final Ticker ticker;
	
	public BfbDefault(final TotalUploaded totup, final Ticker tckr) {
		this.uploaded = totup;
		this.ticker = tckr;
	}
	
	@Override
	public void feed(int read) {
		this.uploaded.add(read);
		this.ticker.execute();
	}

}
