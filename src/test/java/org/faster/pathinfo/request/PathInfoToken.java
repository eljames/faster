package org.faster.pathinfo.request;

public class PathInfoToken {
	
	public String create(String type, String path, long size) {
		return (type.equals("") ? "" : type + "\n") +
				path +
				(size != -1 ? "\n" + size : "");
	}

}
