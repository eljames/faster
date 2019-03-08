package org.faster.feedback.transfer;

public class Ticker {
	
	private final Interval interval;
	private final long period;
	private final TickerAction action;
	
	public Ticker(final long period, final TickerAction ac) {
		this.period = period;
		this.action = ac;
		this.interval = new Interval(System.currentTimeMillis());
	}
	
	public void execute() {
		if(this.period < this.interval.time()) {
			this.action.act(this.interval);
			this.interval.restart();
		}
	}
	
	public void finished() {
		this.action.act(this.interval);
	}
}
