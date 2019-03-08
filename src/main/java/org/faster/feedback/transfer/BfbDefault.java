package org.faster.feedback.transfer;

import org.faster.feedback.BufferFeedback;

public class BfbDefault implements BufferFeedback {
	
	private final TransferedSize transfered;
	private final Ticker ticker;
	
	public BfbDefault(final TransferedSize tz, final Ticker tckr) {
		this.transfered = tz;
		this.ticker = tckr;
	}
	
	@Override
	public void feed(int read) {
		this.transfered.add(read);
		this.ticker.execute();
	}

	@Override
	public void finished() {
		this.ticker.finished();
	}
}
