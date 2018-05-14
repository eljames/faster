package org.faster.pathmap;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.faster.virtualpath.VirtualPath;
import org.faster.virtualpath.VpDefault;
import org.faster.virtualpath.VpMap;

public class PmDefault implements PathMap {
	
	private final Map<String, Path> dirs;
	
	public PmDefault(final Map<String, Path> directories) {
		this.dirs = directories;
	}
	
	@Override
	public VirtualPath get(final CharSequence path) throws FileNotFoundException {
		if(path.equals("/")) {
			return new VpMap(this.dirs);
		}
		Path virtual = Paths.get(path.toString());
		Path realPath = new RealPath(dirs).asReal(virtual);
		File realFile = realPath.toFile();
		if(!realFile.exists()) {
			throw new FileNotFoundException(noVirtualPathMessage(path));
		}
		return new VpDefault(
				new TransformedPath(virtual).rootLevel().asPath(),
				new RealPath(this.dirs).realRoot(virtual),
				realFile);
	}
	
	private String noVirtualPathMessage(CharSequence virtualRoot) {
		return "No virtual path '" + virtualRoot + "' mapped.";
	}

	@Override
	public boolean has(CharSequence path) {
		try {
			Path filePath = new RealPath(this.dirs).asReal(Paths.get(path.toString()));
			return filePath.toFile().exists();
		} catch (FileNotFoundException e) {
			return false;
		}
	}
	
	class RealPath {
		
		private final Map<String, Path> dirs;
		
		public RealPath(final Map<String, Path> directories) {
			this.dirs = directories;
		}
		
		/**
		 * Convert virual path to real path.
		 * @param virtualPath
		 * @return
		 * @throws FileNotFoundException 
		 */
		public Path asReal(Path virtualPath) throws FileNotFoundException {
			
			TransformedPath transformed = new TransformedPath(virtualPath);
			
			String virtualRoot = transformed.firstLevel().asPath().toString();
			
			if(!this.dirs.containsKey(virtualRoot)) {
				throw new FileNotFoundException(noVirtualPathMessage(virtualRoot));
			}
			return concat(virtualRoot, transformed.fromSecond().asPath());
		}
		
		public Path realRoot(Path virtualPath) throws FileNotFoundException {
			TransformedPath transformed = new TransformedPath(virtualPath);
			
			String virtualRoot = transformed.firstLevel().asPath().toString();
			
			if(!this.dirs.containsKey(virtualRoot)) {
				throw new FileNotFoundException(noVirtualPathMessage(virtualRoot));
			}
			
			return Paths.get("/").resolve(this.dirs.get(virtualRoot));
		}
		
		private Path concat(String virtualRootKey, Path rest) {
			return this.dirs.get(virtualRootKey).resolve(rest);
		}
		
	}
	
	final class TransformedPath {
		
		private final Path path;
		
		public TransformedPath(final Path path) {
			this.path = path;
		}
		
		/**
		 * Get the first level from path.
		 * If the path is /home/doc/pdf, the first level is /home.
		 * Considering that the level zero is root '/'.
		 * @return
		 */
		public TransformedPath firstLevel() {
			return new TransformedPath(this.path.getName(0));
		}
		
		public TransformedPath rootLevel() {
			return new TransformedPath(Paths.get("/").resolve(this.firstLevel().asPath()));
		}
		
		/**
		 * Get a new path from the second level.
		 * If the path is /home/docs/pdf/articles/math, the new path will be docs/pdf/articles/math.
		 * In the example before, the second level is 'docs'.
		 * If second level does not exist, it returns a void path "".
		 * The void path makes sense on a path concatenation for example.
		 * @return
		 */
		public TransformedPath fromSecond() {
			if(this.path.getNameCount() > 1)
				return new TransformedPath(this.path.subpath(1, this.path.getNameCount()));
			return new TransformedPath(Paths.get(""));
		}
		
		public Path asPath() {
			return this.path;
		}
	}

}
