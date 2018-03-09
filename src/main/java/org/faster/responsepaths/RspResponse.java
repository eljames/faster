package org.faster.responsepaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import org.faster.pathinfo.response.PtResponse;
import org.faster.sentpath.SentPath;


/**
 * Sends paths to the requester peer.
 * @author Eli James
 *
 */

public class RspResponse implements ResponsePaths {
	
	private final Path root;
	private final SentPath sent;
	private final ResponsePaths response;
	
	public RspResponse(final Path rootPath, final SentPath sentPath, final ResponsePaths responsePath) {
		this.root = rootPath;
		this.sent = sentPath;
		this.response = responsePath;
	}

	@Override
	public void respond(CharSequence relativePath) throws IOException {

		Iterator<Path> paths = Files.list(absolute(Paths.get(relativePath.toString()))).iterator();
		
		while(paths.hasNext())
			sent.send(new PtResponse(this.root, paths.next()));
		
		this.response.respond(relativePath);
		
	}
	
	/**
	 * If root = "/home/usr/share" and relativePath = "/music",
	 * This method returns "/home/usr/share/music". It just concatenate root with relativePath.
	 * @param relativePath
	 * @return
	 */
	private Path absolute(Path relativePath) {
		return this.root.resolve(Paths.get("/").relativize(relativePath));
	}
}
