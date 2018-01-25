package org.faster.pathinfo.request;

public class PathItemsToken {
	
	private final StringBuilder sb;
	
	public PathItemsToken(final StringBuilder sb) {
		this.sb = sb;
	}
	
	public PathItemsToken append(boolean directory, String path, long size) {
		
		this.sb.append("\n\n");
		this.sb.append(new PathInfoToken().create(directory, path, size));
		
		return this;
	}
	
	public String end() {
		return sb.toString() + "\ne";
	}

}
