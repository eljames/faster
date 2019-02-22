package org.faster.feedback.transfer;

public class DeltaSize {
	
	private long initial;
	private final TransferedSize recent;
	
	public DeltaSize(final TransferedSize dz) {
		this.initial = 0;
		this.recent = dz;
	}
	
	public long difference() {
		long difference = recent.size() - this.initial;
		return difference;
	}
	
	public void restart() {
		this.initial = recent.size();
	}

}
