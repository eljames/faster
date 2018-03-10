package org.faster.responsepaths;

import java.io.IOException;
import java.util.Collection;
import org.faster.dirmap.DirMap;
import org.faster.pathinfo.PathInfo;
import org.faster.sentpath.SentPath;


/**
 * Sends paths to the requester peer.
 * @author Eli James
 *
 */

public class RspResponse implements ResponsePaths {
	
	private final DirMap dir;
	private final SentPath sent;
	private final ResponsePaths response;
	
	public RspResponse(final DirMap dirMap, final SentPath sentPath, final ResponsePaths responsePath) {
		this.dir = dirMap;
		this.sent = sentPath;
		this.response = responsePath;
	}

	@Override
	public void respond(CharSequence relativePath) throws IOException {

		Collection<PathInfo> paths = this.dir.paths(relativePath.toString());
		
		for(PathInfo pathInfo : paths)
			sent.send(pathInfo);
		
		this.response.respond(relativePath);
		
	}
}
