package org.faster.feedback;

import org.faster.pathinfo.PathInfo;

public interface FileFeedBack {
	
	/**
	 * Create a {@link BufferFeedBack} object for each download/uploaded file.
	 * @param read
	 * @return
	 */
	BufferFeedBack create(PathInfo info);
	
	public static final FileFeedBack NOTHING = new FileFeedBackNothing();
	
	static class FileFeedBackNothing implements FileFeedBack {

		@Override
		public BufferFeedBack create(PathInfo info) {
			return BufferFeedBack.NOTHING;
		}
		
	}
}
