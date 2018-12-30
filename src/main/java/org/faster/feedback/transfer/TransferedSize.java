package org.faster.feedback.transfer;

public class TransferedSize {
	
	private long downloaded = 0;
	
	public void add(int buffer) {
		this.downloaded = this.downloaded + buffer;
	}
	
	public long size() {
		return this.downloaded;
	}

}
