package org.faster.written;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class WtDefault implements Written {
	
	private final Writer writer;
	
	public WtDefault(final OutputStream out) {
		this.writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
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