package org.faster.filedata;

import java.io.IOException;
import java.io.InputStream;

import org.faster.connection.Connection;
import org.faster.errors.Errors;
import org.faster.errors.ErsNothing;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.token.LineToken;
import org.faster.token.LtDefault;

public class DownloadDefault implements Download {
	
	private final Connection connection;
	private final FileDelivered delivered;
	private final Errors errors;
	
	public DownloadDefault(final Connection con, final FileDelivered deliv) {
		this(con, deliv, new ErsNothing());
	}
	
	public DownloadDefault(final Connection con, final FileDelivered deliv, Errors err) {
		this.connection = con;
		this.delivered = new FileDeliveredNotConsumed(deliv);
		this.errors = err;
	}

	@Override
	public void download(final CharSequence path) throws ProtocolSyntaxErrorException, IOException {
		final InputStream input = this.connection.input();
		final LineToken token = new LtDefault(input);
		Download download = new DownloadResponse(
			token,
			new FdDefault(
				input,
				this.delivered
			),
			this.errors
		);
		download.download(path);
	}
	
	static class DownloadResponse implements Download {
		
		private final LineToken token;
		private final FileData filedata;
		private final Errors errors;
		
		public DownloadResponse(final LineToken tok, FileData file, final Errors ers) {
			this.token = tok;
			this.filedata = file;
			this.errors = ers;
		}

		@Override
		public void download(final CharSequence path) throws ProtocolSyntaxErrorException, IOException {
			String response = this.token.next();
			if(response.equals("k")) {
				this.filedata.download();
				return;
			}
			error(response, path);
		}
		
		private void error(final String response, CharSequence path) throws IOException, ProtocolSyntaxErrorException {
			if(response.equals("err")) {
				this.errors.handle(this.token.next(), path);
				return;
			}
			throw new ProtocolSyntaxErrorException("Expected 'k' or 'err' token.");
		}
		
	}
}
