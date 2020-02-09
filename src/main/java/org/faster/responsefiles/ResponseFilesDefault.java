package org.faster.responsefiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import org.faster.pathinfo.PrintedPathProtocol;
import org.faster.pathinfo.response.PtResponse;
import org.faster.pathmap.PathMap;
import org.faster.virtualpath.VirtualPath;
import org.faster.written.Written;
import org.faster.written.WtDefault;

public class ResponseFilesDefault implements ResponseFiles {
	
	private final PathMap pathmap;
	private final OutputStream output;
	private final CreatedSentData sent;
	
	public ResponseFilesDefault(final PathMap map, final OutputStream out, final CreatedSentData created) {
		this.pathmap = map;
		this.output = out;
		this.sent = created;
	}

	@Override
	public void send(CharSequence path) throws IOException {
		Written written = new WtDefault(this.output);
		new RfFileNotFound(
			new RfOK(
				new RfFiles(
					new RfFinished(written),
					this.pathmap,
					written,
					sent,
					this.output
				), written
			),
			pathmap,
			written
		).send(path);
	}
	
	static class RfFileNotFound implements ResponseFiles {
		
		private final ResponseFiles origin;
		private final PathMap pathmap;
		private final Written written;
		
		public RfFileNotFound(final ResponseFiles response, final PathMap map, final Written wt) {
			this.origin = response;
			this.pathmap = map;
			this.written = wt;
		}

		@Override
		public void send(CharSequence path) throws IOException {
			if(!this.pathmap.has(path)) {
				error();
				return;
			}
			this.origin.send(path);
		}
		
		private void error() throws IOException {
			this.written
			.write("err")
			.writeLine()
			.write("fnf") // File or directory not found
			.writeLine()
			.flush();
		}
	}
	
	static class RfOK implements ResponseFiles {
		
		private final ResponseFiles origin;
		private final Written written;
		
		public RfOK(final ResponseFiles response, final Written wt) {
			this.origin = response;
			this.written = wt;
		}

		@Override
		public void send(CharSequence path) throws IOException {
			this.written
				.write("k")
				.writeLine()
				.flush();
			this.origin.send(path);
		}
	}
	
	static class RfFiles implements ResponseFiles {
		
		private final ResponseFiles origin;
		private final PathMap pathmap;
		private final Written written;
		private final CreatedSentData sent;
		private final OutputStream output;
		
		public RfFiles(final ResponseFiles response, final PathMap map, final Written wt, final CreatedSentData created, final OutputStream out) {
			this.pathmap = map;
			this.origin = response;
			this.written = wt;
			this.sent = created;
			this.output = out;
		}

		@Override
		public void send(CharSequence path) throws IOException {
			VirtualPath virtual = this.pathmap.get(path);
			if(virtual.isDirectory()) {
				final SentData sentdata = this.sent.directory(virtual, this.output);
				this.directory(virtual, sentdata);
				sentdata.finished();
			}
			else {
				final SentData sentdata = this.sent.file(virtual, this.output);
				this.singlefile(virtual, sentdata);
				sentdata.finished();
			}
			this.origin.send(path);
		}
		
		private void directory(final VirtualPath virtual, final SentData sentdata) throws IOException {
			/*VirtualPath cached = new VpCachedSize(virtual, virtual.size());
			PrintedPath printed = new PrintedPathDirectory(
				new PrintedPathProtocol(
					new PtResponse(virtual)
				),
				cached
			);*/
			this.written
				.write("-1")
				.writeLine()
				.flush();
			this.recursive(virtual, sentdata);
		}
		
		private void recursive(final VirtualPath virtual, final SentData sentdata) throws IOException {
			Collection<VirtualPath> paths = virtual.paths();
			for(VirtualPath item : paths) {
				if(item.isDirectory()) {
					this.recursive(item, sentdata);
				} else {
					this.written
						.writeLine()
						.flush();
					this.singlefile(item, sentdata);
				}
			}
		}
		
		private void singlefile(final VirtualPath virtual, final SentData sentdata) throws IOException {
			String printed = new PrintedPathProtocol(
				new PtResponse(virtual)
			).print();
			this.written
				.write(printed)
				.flush();
			filedata(virtual, sentdata);
		}

		private void filedata(final VirtualPath virtual, final SentData sentdata) throws IOException {
			sentdata.send(virtual);
		}
	}
	
	static class RfFinished implements ResponseFiles {
		
		private final Written written;
		
		public RfFinished(final Written wt) {
			this.written = wt;
		}

		@Override
		public void send(CharSequence path) throws IOException {
			this.written
				.write("e")
				.writeLine()
				.flush();
		}
	}
	
	static class VpCachedSize implements VirtualPath {
		
		private final VirtualPath origin;
		private final long size;
		
		public VpCachedSize(final VirtualPath path, final long size) {
			this.origin = path;
			this.size = size;
		}

		@Override
		public CharSequence path() {
			return this.origin.path();
		}

		@Override
		public Collection<VirtualPath> paths() throws IOException {
			return this.origin.paths();
		}

		@Override
		public File real() throws FileNotFoundException {
			return this.origin.real();
		}

		@Override
		public boolean isDirectory() {
			return this.origin.isDirectory();
		}

		@Override
		public long size() throws IOException {
			return this.size;
		}
	}
}
