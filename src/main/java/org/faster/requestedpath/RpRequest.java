package org.faster.requestedpath;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathitems.PathItems;

public class RpRequest implements RequestedPaths {
	
	private final RequestedPaths req;
	private final OutputStream out;
	
	private static final String PATH_ITEMS = "pi";
	private static final String LINEBREAK = "\n";

	public RpRequest(RequestedPaths requestedPaths, OutputStream outputStream) {
		this.req = requestedPaths;
		this.out = outputStream;
	}

	@Override
	public PathItems request(CharSequence path) throws IOException, ProtocolSyntaxErrorException {
		
		doRequest(
			new OutputStreamWriter(this.out, StandardCharsets.UTF_8),
			path
		);

		return req.request(path);
		
	}
	
	private void doRequest(Writer writer, CharSequence path) throws IOException {
		writer.write(PATH_ITEMS);
		writer.write(LINEBREAK);
		writer.write(path.toString());
		writer.flush();
	}

}