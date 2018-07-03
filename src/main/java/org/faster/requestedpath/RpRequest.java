package org.faster.requestedpath;

import java.io.IOException;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathitems.PathItems;
import org.faster.written.Written;

public class RpRequest implements RequestedPaths {
	
	private final RequestedPaths req;
	private final Written written;
	
	private static final String PATH_ITEMS = "pi";

	public RpRequest(RequestedPaths requestedPaths, Written writenStream) {
		this.req = requestedPaths;
		this.written = writenStream;
	}

	@Override
	public PathItems request(CharSequence path) throws IOException, ProtocolSyntaxErrorException {
		
		this.written
			.write(PATH_ITEMS)
			.writeLine()
			.write(path.toString())
			.writeLine()
			.flush();

		return req.request(path);
		
	}

}
