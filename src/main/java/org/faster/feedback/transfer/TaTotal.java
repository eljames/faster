package org.faster.feedback.transfer;

import org.faster.server.uploads.creation.TransferElement;

public class TaTotal implements TickerAction {
	private final TransferElement upload;
	private final DeltaSize sizediff;
	private final SizeLeft sizeleft;
	private final TransferedSize size;
	
	public TaTotal(final TransferElement element, final DeltaSize ds, final SizeLeft sl, final TransferedSize ts) {
		this.upload = element;
		this.sizediff = ds;
		this.sizeleft = sl;
		this.size = ts;
	}

	@Override
	public void act(Interval interval) {
		final TransferRate rate = new TransferRate(this.sizediff, interval);
		this.upload.downloaded(() -> size.size());
		this.upload.rate(rate);
		this.upload.timeleft(
			new TimeLeft(
				this.sizeleft,
				rate
			)
		);
		this.sizediff.restart();
	}
}
