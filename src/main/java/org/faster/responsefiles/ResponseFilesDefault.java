package org.faster.responsefiles;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import org.faster.pathinfo.PrintedPath;
import org.faster.pathinfo.PrintedPathDirectory;
import org.faster.pathinfo.PrintedPathProtocol;
import org.faster.pathinfo.response.PtResponse;
import org.faster.pathmap.PathMap;
import org.faster.virtualpath.VirtualPath;
import org.faster.written.Written;
import org.faster.written.WtDefault;

public class ResponseFilesDefault implements ResponseFiles {
	
	private final PathMap pathmap;
	private final OutputStream output;
	private final SentData sent;
	
	public ResponseFilesDefault(final PathMap map, final OutputStream out, final SentData snd) {
		this.pathmap = map;
		this.output = out;
		this.sent = snd;
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
					sent
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
		private final SentData sentdata;
		
		public RfFiles(final ResponseFiles response, final PathMap map, final Written wt, final SentData sent) {
			this.pathmap = map;
			this.origin = response;
			this.written = wt;
			this.sentdata = sent;
		}

		@Override
		public void send(CharSequence path) throws IOException {
			VirtualPath virtual = this.pathmap.get(path);
			if(virtual.isDirectory()) {
				this.directory(virtual);
			}
			else {
				this.singlefile(virtual);
			}
			this.origin.send(path);
		}
		
		private void directory(final VirtualPath virtual) throws IOException {
			PrintedPath printed = new PrintedPathDirectory(
				new PrintedPathProtocol(
					new PtResponse(virtual)
				),
				virtual.real()
			);
			this.written
				.write(printed.print())
				.writeLine()
				.flush();
			this.recursive(virtual);
		}
		
		private void recursive(final VirtualPath virtual) throws IOException {
			Collection<VirtualPath> paths = virtual.paths();
			for(VirtualPath item : paths) {
				if(item.isDirectory()) {
					this.recursive(item);
				} else {
					this.written
						.writeLine()
						.flush();
					this.singlefile(item);
				}
			}
		}
		
		private void singlefile(final VirtualPath virtual) throws IOException {
			String printed = new PrintedPathProtocol(
				new PtResponse(virtual)
			).print();
			this.written
				.write(printed)
				.flush();
			filedata(virtual);
		}

		private void filedata(final VirtualPath virtual) throws IOException {
			this.sentdata.send(virtual);
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
}
