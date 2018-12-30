package org.faster.feedback;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FriendlySize {
	
	private static final long KB = 1000;
	private static final long MB = KB * KB;
	private static final long GB = KB * MB;
	
	public FriendlySize() {}
	
	public String print(long size) {
		int scale = 2;
		if(size > GB) {
			return divstr(size, GB, scale, "GB");
		}
		if(size > MB) {
			return divstr(size, MB, scale, "MB");
		}
		if(size > KB) {
			return divstr(size, KB, scale, "KB");
		}
		return divstr(size, 1, scale, "KB");
	}
	
	private BigDecimal div(long size, long unit, int scale) {
		return new BigDecimal(size).divide(new BigDecimal(unit), scale, RoundingMode.HALF_DOWN);
	}
	
	private String divstr(long size, long unit, int scale, String textunit) {
		return div(size, unit, scale).toPlainString() + textunit;
	}
}
