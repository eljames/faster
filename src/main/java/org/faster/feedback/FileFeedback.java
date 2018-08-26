package org.faster.feedback;

import org.faster.pathinfo.PathInfo;

public interface FileFeedback {
	
	/**
	 * Create a {@link BufferFeedback} object for each download/uploaded file.
	 * @param read
	 * @return
	 */
	BufferFeedback create(PathInfo info);
	
	public static final FileFeedback NOTHING = new FileFeedBackNothing();
	
	static class FileFeedBackNothing implements FileFeedback {

		@Override
		public BufferFeedback create(PathInfo info) {
			return BufferFeedback.NOTHING;
		}
		
	}
}
