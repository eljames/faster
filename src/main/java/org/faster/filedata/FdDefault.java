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
	
	private final FileDelivered delivered;
	
	public FdDefault(final FileDelivered deliv) {
		this.delivered = new FileDeliveredNotConsumed(deliv);
	}
	
	@Override
	public void download(final InputStream input) throws IOException, ProtocolSyntaxErrorException {
		LineToken token = new LtDefault(input);
		boolean directorySize = true;
		treatment(
			new PtSize(
				new PtPath(
					new PtType(token),token
				), token, directorySize
			), token, input
		);
	}
	
	private void treatment(PathInfo info, LineToken token, InputStream input) throws IOException, ProtocolSyntaxErrorException {
		if(info.isDirectory()) {
			new FdIterable(token, this.delivered).download(input);
			return;
		}
		this.delivered.delivery(new SingleFileStream(input, info.size()), info);
	}
	
	static class FdIterable implements FileData {
		
		private final LineToken token;
		private final FileDelivered delivered;
		
		public FdIterable(final LineToken tok, final FileDelivered deliv) {
			this.token = tok;
			this.delivered = deliv;
		}

		@Override
		public void download(InputStream input) throws IOException, ProtocolSyntaxErrorException {
			String line = this.token.next();
			while(line.equals("")) {
				new FdFile(this.delivered, this.token).download(input);
				line = this.token.next();
			}
			if(!line.equals("e")) {
				throw new ProtocolSyntaxErrorException("The token 'e' or [void token] expect");
			}
		}
		
	}
}
