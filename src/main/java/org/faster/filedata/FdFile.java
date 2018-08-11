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
	
	private final FileDelivered delivered;
	private final LineToken token;

	public FdFile(final FileDelivered deliv, final LineToken tok) {
		this.delivered = deliv;
		this.token = tok;
	}

	@Override
	public void download(InputStream input) throws IOException, ProtocolSyntaxErrorException {
		PathInfo info = path(this.token);
		this.delivered.delivery(new SingleFileStream(input, info.size()), info);
	}
	
	private PathInfo path(LineToken token) throws IOException, ProtocolSyntaxErrorException {
		return new PtSize(
			new PtPath(
				new PtType(token),token
			), token
		);
	}
}
