package org.faster.virtualpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

public class VpDefault implements VirtualPath {
	
	final String virtualRoot;
	final Path realRoot;
	final File realFile;
	
	public VpDefault(final String virtualRoot, Path realRoot, final File file) {
		this.virtualRoot = virtualRoot;
		this.realRoot = realRoot;
		this.realFile = file;
	}

	@Override
	public CharSequence path() {
		return Paths.get(this.virtualRoot).resolve(
			this.realRoot.relativize(this.realFile.toPath())
		).toString().replace(File.separator, "/");
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

	@Override
	public long size() throws IOException {
		Path folder = this.realFile.toPath();
	    long size = Files.walk(folder)
	      .filter(p -> p.toFile().isFile())
	      .mapToLong(p -> p.toFile().length())
	      .sum();
	    return size;
	}

}
