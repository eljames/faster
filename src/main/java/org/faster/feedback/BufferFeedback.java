package org.faster.feedback;

public interface BufferFeedback {
	
	/**
	 * This method says to a BufferFeedBack object that a amount of bytes {@code read} has been written.
	 * For instance, it can be used to notify each piece of data already written to a user interface. For instance, if this method was called 5 times
	 * , and each time {@code read} was 20, it means that was read 5 x 20 bytes = 100 bytes already read.
	 */ 
	void feed(int read);
	void finished();
	
	public static final BufferFeedBackNothing NOTHING = new BufferFeedBackNothing();
	
	static class BufferFeedBackNothing implements BufferFeedback {

		@Override
		public void feed(int read) {
			// Nothing
		}

		@Override
		public void finished() {
			// Nothing
		}
	}

}
