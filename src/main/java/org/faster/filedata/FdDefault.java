package org.faster.filedata;

import java.io.IOException;
import java.io.InputStream;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.PathInfo;
import org.faster.pathinfo.request.PtPath;
import org.faster.pathinfo.request.PtSize;
import org.faster.pathinfo.request.PtType;
import org.faster.token.LineToken;
import org.faster.token.LtDefault;

public class FdDefault implements FileData {
	
	private final InputStream input;
	private final FileDelivered delivered;
	
	public FdDefault(final InputStream in, final FileDelivered deliv) {
		this.input = in;
		this.delivered = new FileDeliveredNotConsumed(deliv);
	}
	
	@Override
	public void download() throws IOException, ProtocolSyntaxErrorException {
		LineToken token = new LtDefault(this.input);
		boolean directorySize = true;
		treatment(
			new PtSize(
				new PtPath(
					new PtType(token),token
				), token, directorySize
			), token
		);
	}
	
	private void treatment(final PathInfo info, final LineToken token) throws IOException, ProtocolSyntaxErrorException {
		if(info.isDirectory()) {
			new FdIterable(token, this.input, this.delivered).download();
			return;
		}
		this.delivered.delivery(new SingleFileStream(this.input, info.size()), info);
	}
	
	static class FdIterable implements FileData {
		
		private final LineToken token;
		private final InputStream input;
		private final FileDelivered delivered;
		
		public FdIterable(final LineToken tok, InputStream in, final FileDelivered deliv) {
			this.token = tok;
			this.input = in;
			this.delivered = deliv;
		}

		@Override
		public void download() throws IOException, ProtocolSyntaxErrorException {
			String line = this.token.next();
			while(line.equals("")) {
				new FdFile(this.token, this.input, this.delivered).download();
				line = this.token.next();
			}
			if(!line.equals("e")) {
				throw new ProtocolSyntaxErrorException("The token 'e' or [void token] expect");
			}
		}
		
	}
}
