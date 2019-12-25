package org.faster.filedata.continuedownload;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import org.faster.connection.Connection;
import org.faster.exception.ProtocolSyntaxErrorException;
import org.faster.pathinfo.PathInfo;
import org.faster.written.Written;
import org.faster.written.WtDefault;

public class RequestedDownloads {
	
	private PathDownload pathDownload;
	private Connection con;
	private Collection<PathInfo> paths;

	public RequestedDownloads(PathDownload pathDownload, Connection con, Collection<PathInfo> paths) {
		this.pathDownload = pathDownload;
		this.con = con;
		this.paths = paths;
	}
	
	public void downloadAll() throws IOException, ProtocolSyntaxErrorException {
		final ContinueDownload contDownload = new ContinueDownload(con);
		// Request downloads from server.
		contDownload.start();
		for(PathInfo info : paths) {
			// Send "c" to server to indicate you want download a next file or directory.
			contDownload.next();
			this.pathDownload.download(info);
		}
		contDownload.finished();
	}
	
	
	private static class ContinueDownload {
		
		private Connection con;
		
		public ContinueDownload(Connection con) {
			this.con = con;
		}
		
		public void start() throws IOException {
			final OutputStream output = this.con.output();
			final Written written = new WtDefault(output);
			written
			.write("fd")
			.writeLine()
			.flush();
		}

		public void next() throws IOException {
			final OutputStream output = this.con.output();
			final Written written = new WtDefault(output);
			written
				.write("c")
				.writeLine()
				.flush();
		}
		
		public void finished() throws IOException {
			final OutputStream output = this.con.output();
			final Written written = new WtDefault(output);
			written
				.write("df")
				.writeLine()
				.flush();
		}
	}
}
