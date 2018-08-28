package org.faster.responsepaths;

import java.io.IOException;
import org.faster.written.Written;

/**
 * Responsible to write the 'end' stream. The end stream shows that all paths was sent and there's nothing to send anymore.
 * @author Eli James
 *
 */
public class RspFinished implements ResponsePaths {
	
	private final Written written;
	
	public RspFinished(final Written written) {
		this.written = written;
	}

	@Override
	public void respond(CharSequence relativePath) throws IOException {
		this.written
		.write("e")
		.writeLine()
		.flush();
		
	}
}
