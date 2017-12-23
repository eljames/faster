package org.faster.pathinfo.request;

public class PathInfoToken {
	
	private String type;
	private String path;
	private long size;
	
	
	public PathInfoToken(String type, String path, long size) {
		this.type = type;
		this.path = path;
		this.size = size;
	}
	
	public String create() {
		return (this.type.equals("") ? "" : this.type + "\n") +
				this.path +
				(size != -1 ? "\n" + size : "");
	}

}
