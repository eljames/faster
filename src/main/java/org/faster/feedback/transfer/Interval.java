package org.faster.feedback.transfer;

public class Interval {
	
	// Data bag
	private long initial;
	
	public Interval(final long initial) {
		this.initial = initial;
	}

	public void restart() {
		this.initial = System.currentTimeMillis();
	}
	
	public long time() {
		return System.currentTimeMillis() - this.initial;
	}
}
