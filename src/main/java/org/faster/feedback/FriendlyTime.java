package org.faster.feedback;

public class FriendlyTime {
	
	private static long MIN = 60;
	private static long HOUR = MIN*MIN;
	
	public String time(long seconds) {
		return hour(seconds) + "h " + minute(seconds) + "m " + second(seconds) + "s";
	}
	
	public long hour(long seconds) {
		return seconds / HOUR;
	}
	
	public long minute(long seconds) {
		long rest = seconds % HOUR;
		return rest / MIN;
	}
	
	public long second(long seconds) {
		long rest = (seconds % HOUR) % MIN;
		return rest;
	}
}
