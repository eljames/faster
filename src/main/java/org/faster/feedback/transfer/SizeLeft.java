package org.faster.feedback.transfer;

public class SizeLeft {
	
	private final TransferedSize downloaded;
	private final long total;
	
	public SizeLeft(final TransferedSize dl, final long total) {
		this.downloaded = dl;
		this.total = total;
	}
	
	public long size() {
		return this.total - downloaded.size();
	}

}
