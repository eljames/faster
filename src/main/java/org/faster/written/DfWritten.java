package org.faster.written;

import java.io.IOException;
import java.io.Writer;

public class DfWritten implements Written {
	
	private final Writer writer;
	
	public DfWritten(final Writer writer) {
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
	 * @see org.faster.written.Written#writeIf(boolean, java.lang.CharSequence)
	 */
	@Override
	public Written writeIf(boolean b, CharSequence charSeq) throws IOException {
		if(b) write(charSeq);
		return this;
	}
	
	/* (non-Javadoc)
	 * @see org.faster.written.Written#writeIf(boolean, long)
	 */
	@Override
	public Written writeIf(boolean b, long num) throws IOException {
		if(b) write(num);
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