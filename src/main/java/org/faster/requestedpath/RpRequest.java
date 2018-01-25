package org.faster.requestedpath;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathitems.PathItems;
import org.faster.written.Written;

public class RpRequest implements RequestedPaths {
	
	private final RequestedPaths req;
	private final Written written;
	
	private static final String PATH_ITEMS = "pi";
	private static final String LINEBREAK = "\n";

	public RpRequest(RequestedPaths requestedPaths, Written writenStream) {
		this.req = requestedPaths;
		this.written = writenStream;
	}

	@Override
	public PathItems request(CharSequence path) throws IOException, ProtocolSyntaxErrorException {
		
		this.written
			.write(PATH_ITEMS)
			.write(LINEBREAK)
			.write(path.toString())
			.flush();

		return req.request(path);
		
	}

}
