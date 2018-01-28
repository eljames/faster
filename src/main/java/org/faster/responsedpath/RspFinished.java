package org.faster.responsedpath;

import java.io.IOException;
import java.nio.file.Path;

import org.faster.written.Written;

/**
 * Responsible to write the 'end' stream. The end stream show that all the paths was sent and there's nothing to send anymore.
 * @author Eli James
 *
 */
public class RspFinished implements ResponsedPath {
	
	private final Written written;
	
	public RspFinished(final Written written) {
		this.written = written;
	}

	@Override
	public void respond(Path relativePath) throws IOException {
		this.written
		.write("\n")
		.write("e");
		
	}
}
