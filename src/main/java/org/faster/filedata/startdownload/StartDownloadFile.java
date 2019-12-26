package org.faster.filedata.startdownload;

import java.io.IOException;
import java.io.OutputStream;
import org.faster.connection.Connection;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.written.Written;
import org.faster.written.WtDefault;

public class StartDownloadFile {
	
	private PathsDownload pathsDownload;
	private Connection con;
	
	public StartDownloadFile(PathsDownload pathsDownload, Connection con) {
		this.pathsDownload = pathsDownload;
		this.con = con;
	}
	
	public void start() throws IOException, ProtocolSyntaxErrorException {
		// Request downloads from server.
		this.startToken();
		this.pathsDownload.download();
		this.finished();
	}
	
	public void finished() throws IOException {
		final OutputStream output = this.con.output();
		final Written written = new WtDefault(output);
		written
			.write("df")
			.writeLine()
			.flush();
	}
	
	public void startToken() throws IOException {
		final OutputStream output = this.con.output();
		final Written written = new WtDefault(output);
		written
		.write("fd")
		.writeLine()
		.flush();
	}
	
	
	public static class ContinueDownload {
		
		private Connection con;
		private PathsDownload download;
		
		public ContinueDownload(PathsDownload download, Connection con) {
			this.download = download;
			this.con = con;
		}
		
		public void next() throws IOException, ProtocolSyntaxErrorException {
			this.nextToken();
			this.download.download();
		}

		public void nextToken() throws IOException {
			final OutputStream output = this.con.output();
			final Written written = new WtDefault(output);
			written
				.write("c")
				.writeLine()
				.flush();
		}
	}
}
