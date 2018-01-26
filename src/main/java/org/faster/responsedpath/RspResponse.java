package org.faster.responsedpath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import org.faster.pathinfo.response.PtResponse;
import org.faster.sentpath.SentPath;


/**
 * Sends paths to the requester peer.
 * @author Eli James
 *
 */

public class RspResponse implements ResponsedPath {
	
	private final Path root;
	private final SentPath sent;
	
	public RspResponse(final Path rootPath, final SentPath sentPath) {
		this.root = rootPath;
		this.sent = sentPath;
	}

	@Override
	public void respond(Path relativePath) throws IOException {

		Iterator<Path> paths = paths(relativePath);
		
		while(paths.hasNext())
			sent.send(new PtResponse(this.root, paths.next()));
		
	}
	
	private Iterator<Path> paths(Path relativePath) throws IOException {
		Path absolutePath = this.root.resolve(relativePath);
		return Files.list(absolutePath).iterator();
	}
}
