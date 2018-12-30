package org.faster.feedback.transfer;

public class TimeLeft {
	
	private final SizeLeft sizeleft;
	private final TransferRate rate;
	
	public TimeLeft(final SizeLeft sl, final TransferRate tr) {
		this.sizeleft = sl;
		this.rate = tr;
	}
	
	public long time() {
		long timeleft = sizeleft.size() / (long)rate.rate();
		return timeleft;
	}
}
