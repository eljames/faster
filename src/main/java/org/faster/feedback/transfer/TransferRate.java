package org.faster.feedback.transfer;

public class TransferRate {
	
	private final DeltaSize diff;
	private final Interval interval;
	
	public TransferRate(final DeltaSize diff, final Interval inter) {
		this.diff = diff;
		this.interval = inter;
	}
	
	/**
	 * 
	 * @return The rate of bytes per second
	 */
	public double rate() {
		double second = ((double)interval.time()) / 1000;
		long sizediff = diff.difference();
		return ((double)sizediff) / second;
	}
}
