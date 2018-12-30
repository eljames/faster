package org.faster.feedback.transfer;

public class Interval {
	
	// Data bag
	private long initial;
	private long interval;
	
	public Interval(final long initial) {
		this.initial = initial;
	}

	public long calculate() {
		long end = System.currentTimeMillis();
		this.interval = end - initial;
		this.initial = end;
		return interval;
	}
	
	public long time() {
		return this.interval;
	}
}
