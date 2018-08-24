package org.faster.filedata;

import java.io.IOException;
import java.io.InputStream;

import org.faster.connection.Connection;
import org.faster.errors.Errors;
import org.faster.errors.ErsNothing;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.token.LineToken;
import org.faster.token.LtDefault;
import org.faster.written.Written;
import org.faster.written.WtDefault;

public class DownloadDefault implements Download {
	
	private final Connection connection;
	private final FileDelivered delivered;
	private final Errors errors;
	
	public DownloadDefault(final Connection con, final FileDelivered deliv) {
		this(con, deliv, new ErsNothing());
	}
	
	public DownloadDefault(final Connection con, final FileDelivered deliv, Errors err) {
		this.connection = con;
		this.delivered = deliv;
		this.errors = err;
	}

	@Override
	public void download(final CharSequence path) throws ProtocolSyntaxErrorException, IOException {
		InputStream input = this.connection.input();
		LineToken token = new LtDefault(input);
		Written written = new WtDefault(this.connection.output());
		Download download = new DownloadRequest(
			new DownloadResponse(
				token,
				new FdDefault(input, this.delivered),
				this.errors
			),
			written
		);
		download.download(path);
	}
	
	/**
	 * Sends file request to server according to given path.
	 * @author Eli James Aguiar
	 *
	 */
	static class DownloadRequest implements Download {
		
		private final Download origin;
		private final Written written;
	
		public DownloadRequest(final Download download, final Written wt) {
			this.written = wt;
			this.origin = download;
		}

		@Override
		public void download(final CharSequence path) throws ProtocolSyntaxErrorException, IOException {
			this.written
				.write("fd")
				.writeLine()
				.write(path)
				.writeLine()
				.flush();
			this.origin.download(path);
		}
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
			if(response.equals("err")) {
				this.errors.handle(this.token.next(), path);
				return;
			}
			throw new ProtocolSyntaxErrorException("Expected 'k' or 'err' token.");
		}
		
	}
}
