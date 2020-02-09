package org.faster.sentsize;

import java.io.FileNotFoundException;
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
		final Written written = new WtDefault(this.con.output());
		try {
			VirtualPath virtual = this.path.get(virtualPath);
			sendSize(virtual, written);
		} catch (FileNotFoundException e) {
			written
				.write("err")
				.writeLine()
				.write("fnf")
				.writeLine()
				.flush();
		}
	}
	
	
	private void sendSize(final VirtualPath virtual, Written written) throws IOException {
		written
			.write("k")
			.writeLine()
			.write(String.valueOf(new RecursiveSize(virtual).size()))
			.writeLine()
			.flush();
	}


	static class RecursiveSize {
		
		private final VirtualPath file;

		public RecursiveSize(VirtualPath virtual) {
			this.file = virtual;
		}
		
		public long size() throws IOException {
			return recursiveSize(this.file);
		}
		
		private long recursiveSize(VirtualPath fileRec) throws IOException {
			if(fileRec.isDirectory()) {
				long size = 0;
				Collection<VirtualPath> files = fileRec.paths();
				for(final VirtualPath oneFile : files) {
					size = size + recursiveSize(oneFile);
				}
				return size;
			}
			return fileRec.size();
		}
	}
	
	
}
