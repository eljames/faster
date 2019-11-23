package org.faster.sentsize;

import java.io.IOException;
import java.util.Collection;

import org.faster.connection.Connection;
import org.faster.pathmap.PathMap;
import org.faster.token.LineToken;
import org.faster.token.LtDefault;
import org.faster.virtualpath.VirtualPath;
import org.faster.written.Written;
import org.faster.written.WtDefault;

public class SentSize {
	
	
	private final Connection con;
	private final PathMap path;
	
	public SentSize(final Connection con, final PathMap path) {
		this.con = con;
		this.path = path;
	}
	
	
	public void send() throws IOException {
		final LineToken lineToken = new LtDefault(this.con.input());
		final String virtualPath = lineToken.next();
		VirtualPath virtual = this.path.get(virtualPath);
		sendSize(virtual);
	}
	
	
	private void sendSize(final VirtualPath virtual) throws IOException {
		Written written = new WtDefault(this.con.output());
		written
			.write("k")
			.writeLine()
			.write(String.valueOf(new RecursiveSize(virtual).size()))
			.writeLine();
	}


	static class RecursiveSize {
		
		private final VirtualPath file;

		public RecursiveSize(VirtualPath virtual) {
			this.file = virtual;
		}
		
		public long size() throws IOException {
			return recursiveSize(this.file);
		}
		
		private long recursiveSize(VirtualPath file2) throws IOException {
			long size = 0;
			if(file.isDirectory()) {
				Collection<VirtualPath> files = file.paths();
				for(final VirtualPath oneFile : files) {
					size = size + recursiveSize(oneFile);
				}
			} else {
				size = size + file.size();
			}
			return size;
		}
	}
	
	
}
