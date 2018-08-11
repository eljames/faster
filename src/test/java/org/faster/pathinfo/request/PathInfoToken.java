package org.faster.pathinfo.request;

public class PathInfoToken {
	
	public String create(boolean directory, String path, long size) {
		return this.create(directory, path, size, false);
	}
	
	public String create(boolean directory, String path, long size, boolean folderSize) {
		return (directory ? "d" : "f") + "\n" +
				path +
				(size != -1 || folderSize ? "\n" + size : "");
	}

}
