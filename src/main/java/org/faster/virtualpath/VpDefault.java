package org.faster.virtualpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class VpDefault implements VirtualPath {
	
	final Path virtualRoot;
	final Path realRoot;
	final File realFile;
	
	public VpDefault(final Path virtualRoot, Path realRoot, final File file) {
		this.virtualRoot = virtualRoot;
		this.realRoot = realRoot;
		this.realFile = file;
	}

	@Override
	public CharSequence path() {
		return this.virtualRoot.resolve(
			this.realRoot.relativize(this.realFile.toPath())
		).toString();
	}

	@Override
	public Collection<VirtualPath> paths() throws UnsupportedOperationException, IOException {
		if(!this.realFile.isDirectory()) {
			throw new UnsupportedOperationException();
		}
		return pathCollection();
	}
	
	private Collection<VirtualPath> pathCollection() throws IOException {
		
		final ArrayList<VirtualPath> paths = new ArrayList<VirtualPath>();
		for(final File file : this.realFile.listFiles()) {
			paths.add(new VpDefault(this.virtualRoot, this.realRoot, file));
		}
		return paths;
	}

	@Override
	public File real() throws FileNotFoundException {
		return this.realFile;
	}

	@Override
	public boolean isDirectory() {
		return this.realFile.isDirectory();
	}

}
