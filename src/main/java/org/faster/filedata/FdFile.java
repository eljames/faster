package org.faster.filedata;

import java.io.IOException;
import java.io.InputStream;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.PathInfo;
import org.faster.pathinfo.request.PtPath;
import org.faster.pathinfo.request.PtSize;
import org.faster.pathinfo.request.PtType;
import org.faster.token.LineToken;

public class FdFile implements FileData {
	
	private final LineToken token;
	private final InputStream input;
	private final HandledFile handled;

	public FdFile(final LineToken tok, InputStream in,final HandledFile hdl) {
		this.token = tok;
		this.input = in;
		this.handled = hdl;
	}

	@Override
	public void download() throws IOException, ProtocolSyntaxErrorException {
		PathInfo info = path(this.token);
		this.handled.handle(new SingleFileStream(this.input, info.size()), info);
	}
	
	private PathInfo path(LineToken token) throws IOException, ProtocolSyntaxErrorException {
		return new PtSize(
			new PtPath(
				new PtType(token),token
			), token
		);
	}
}
