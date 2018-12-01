package org.faster.responsefiles;

import java.io.OutputStream;

import org.faster.feedback.BufferFeedback;
import org.faster.virtualpath.VirtualPath;

public class CreatedSentDataDefault implements CreatedSentData {

	@Override
	public SentData directory(VirtualPath dir, OutputStream out) {
		return new SdDefault(out, BufferFeedback.NOTHING);
	}

	@Override
	public SentData file(VirtualPath dir, OutputStream out) {
		return new SdDefault(out, BufferFeedback.NOTHING);
	}

}
