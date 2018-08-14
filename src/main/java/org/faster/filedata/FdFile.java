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
	private final FileDelivered delivered;

	public FdFile(final LineToken tok, InputStream in,final FileDelivered deliv) {
		this.token = tok;
		this.input = in;
		this.delivered = deliv;
	}

	@Override
	public void download() throws IOException, ProtocolSyntaxErrorException {
		PathInfo info = path(this.token);
		this.delivered.delivery(new SingleFileStream(this.input, info.size()), info);
	}
	
	private PathInfo path(LineToken token) throws IOException, ProtocolSyntaxErrorException {
		return new PtSize(
			new PtPath(
				new PtType(token),token
			), token
		);
	}
}
