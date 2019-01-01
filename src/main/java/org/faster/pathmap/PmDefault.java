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
		Path realPath = new RealPath(dirs).asReal(path.toString());
		File realFile = realPath.toFile();
		if(!realFile.exists()) {
			throw new FileNotFoundException(noVirtualPathMessage(path));
		}
		return new VpDefault(
			new TransformedPath(path.toString()).rootLevel().asPath(),
			new RealPath(this.dirs).realRoot(path.toString()),
			realFile
		);
	}
	
	private String noVirtualPathMessage(CharSequence virtualRoot) {
		return "No virtual path '" + virtualRoot + "' mapped.";
	}

	@Override
	public boolean has(CharSequence path) {
		try {
			if(path.equals("/")) {
				return true;
			}
			Path filePath = new RealPath(this.dirs).asReal(path.toString());
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
		public Path asReal(String virtualPath) throws FileNotFoundException {
			TransformedPath transformed = new TransformedPath(virtualPath);
			String virtualRoot = "/" + transformed.firstLevel().asPath();
			if(!this.dirs.containsKey(virtualRoot)) {
				throw new FileNotFoundException(noVirtualPathMessage(virtualRoot));
			}
			return concat(virtualRoot, transformed.fromSecond().asPath());
		}
		
		public Path realRoot(String virtualPath) throws FileNotFoundException {
			TransformedPath transformed = new TransformedPath(virtualPath);
			String virtualRoot = "/" + transformed.firstLevel().asPath();
			if(!this.dirs.containsKey(virtualRoot)) {
				throw new FileNotFoundException(noVirtualPathMessage(virtualRoot));
			}
			return this.dirs.get(virtualRoot);
		}
		
		private Path concat(String virtualRootKey, String rest) {
			return this.dirs.get(virtualRootKey).resolve(rest);
		}
		
	}
	
	final class TransformedPath {
		
		private final String path;
		
		public TransformedPath(final String path) {
			this.path = path;
		}
		
		/**
		 * Get the first level from path.
		 * If the path is /home/doc/pdf, the first level is home.
		 * Considering that the level zero is root '/'.
		 * @return
		 */
		public TransformedPath firstLevel() {
			return new TransformedPath(Paths.get(this.path).getName(0).toString());
		}
		
		public TransformedPath rootLevel() {
			return new TransformedPath("/" + this.firstLevel().asPath());
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
			Path pathobj = Paths.get(this.path);
			if(pathobj.getNameCount() > 1)
				return new TransformedPath(pathobj.subpath(1, pathobj.getNameCount()).toString());
			return new TransformedPath("");
		}
		
		public String asPath() {
			return this.path;
		}
	}

}
