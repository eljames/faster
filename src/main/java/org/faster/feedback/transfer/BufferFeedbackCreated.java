package org.faster.feedback.transfer;

import java.io.IOException;

import org.faster.feedback.BufferFeedback;
import org.faster.server.uploads.creation.DfDefault;
import org.faster.server.uploads.creation.TransferElement;
import org.faster.server.uploads.creation.TransferFile;
import org.faster.virtualpath.VirtualPath;

public class BufferFeedbackCreated {
	private final TransferElement upload;
	private final TransferedSize transferedtotal;
	private final TickerAction action;
	
	public BufferFeedbackCreated(final TransferElement ue, final TransferedSize ts, final TickerAction act) {
		this.upload = ue;
		this.transferedtotal = ts;
		this.action = act;
	}
	
	public BufferFeedback create(final VirtualPath virtual) throws IOException {
		final TransferFile transferfile = this.upload.file(virtual);
		final TransferedSize transferedfile = new TransferedSize();
		return new BfbFile(
			new BfbDefault(
				this.transferedtotal,
				new Ticker(
					1000,
					new TaUI(
						this.action,
						transferfile,
						transferedfile,
						new SizeLeft(transferedfile, virtual.size()),
						new DeltaSize(transferedfile)
					)
				)
			),
			transferedfile
		);
	}
	
	static class BfbFile implements BufferFeedback {
		
		private final BufferFeedback origin;
		private final TransferedSize downloaded;
		
		public BfbFile(final BufferFeedback buffer, final TransferedSize ds) {
			this.origin = buffer;
			this.downloaded = ds;
		}

		@Override
		public void feed(int read) {
			this.downloaded.add(read);
			this.origin.feed(read);
		}

		@Override
		public void finished() {
			this.origin.finished();
		}
	}
	
	static class TaUI implements TickerAction {

		private final TickerAction origin;
		private final TransferFile transferfile;
		private final TransferedSize transferedfilesize;
		private final SizeLeft sizeleft;
		private final DeltaSize delta;
		
		public TaUI(final TickerAction act, final TransferFile tf, final TransferedSize transferedfile, final SizeLeft sl, final DeltaSize ds) {
			this.origin = act;
			this.transferfile = tf;
			this.transferedfilesize = transferedfile;
			this.sizeleft = sl;
			this.delta = ds;
		}
		
		@Override
		public void act(Interval interval) {
			
			this.origin.act(interval);
			this.transferfile.downloaded(new DfDefault(this.transferedfilesize));
			this.transferfile.timeleft(
				new TimeLeft(
					this.sizeleft,
					new TransferRate(
						this.delta,
						interval
					)
				)
			);
			this.delta.restart();
		}
	}
}
