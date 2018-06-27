package org.faster.written;

import java.io.IOException;
import java.io.Writer;

public class WtDefault implements Written {
	
	private final Writer writer;
	
	public WtDefault(final Writer writer) {
		this.writer = writer;
	}
	
	/* (non-Javadoc)
	 * @see org.faster.written.Written#write(java.lang.CharSequence)
	 */
	@Override
	public Written write(CharSequence charSeq) throws IOException {
		this.writer.write(charSeq.toString());
		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.faster.written.Written#write(long)
	 */
	@Override
	public Written write(long num) throws IOException {
		this.writer.write(Long.toString(num));
		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.faster.written.Written#writeLine()
	 */
	@Override
	public Written writeLine() throws IOException {
		this.write("\n");
		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.faster.written.Written#flush()
	 */
	@Override
	public void flush() throws IOException {
		this.writer.flush();
	}
}