package org.faster.sentpath;

import java.io.IOException;

import org.faster.pathinfo.PathInfo;
import org.faster.pathinfo.PrintedPathProtocol;
import org.faster.written.Written;

public class DfSentPath implements SentPath {
	
	
	private final Written written;

	public DfSentPath(final Written written) {
		this.written = written;
	}

	/**
	 * Send a path information according with Protocol.
	 */
	@Override
	public void send(PathInfo pathInfo) throws IOException {
		
		this.written
			.writeLine()
			.writeLine()
			.write(new PrintedPathProtocol(pathInfo).print())
			.flush();		
	}
}
