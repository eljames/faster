package org.faster.pathinfo.request;

public class PathInfoToken {
	
	public String create(boolean directory, String path, long size) {
		return (directory ? "d" : "f") + "\n" +
				path +
				(size != -1 ? "\n" + size : "");
	}

}
