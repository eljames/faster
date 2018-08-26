package org.faster.feedback;

public interface BufferFeedBack {
	
	/**
	 * This method says to a BufferFeedBack object that a byte has been written, and the byte written is the {@code data} parameter.
	 * For instance, it can be used to notify each byte already written to a file. If the file has 2000 bytes in total, and 305 bytes
	 * has been already written, then this method should be called 305 times.
	 */ 
	void feed(long data);
	
	public static final BufferFeedBackNothing NOTHING = new BufferFeedBackNothing();
	
	static class BufferFeedBackNothing implements BufferFeedBack {

		@Override
		public void feed(long data) {
			// Nothing
		}
		
	}

}
