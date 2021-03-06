package org.faster.pathinfo.request;

public class PathItemsToken {
	
	private final StringBuilder sb;
	
	public PathItemsToken(final StringBuilder sb) {
		this.sb = new StringBuilder();
	}
	
	public PathItemsToken() {
		this(new StringBuilder());
	}
	
	public PathItemsToken ok() {
		this.sb.append("k").append("\n");
		return this;
	}
	
	public PathItemsToken append(boolean directory, String path, long size) {
		
		this.sb.append("\n");
		this.sb.append(new PathInfoToken().create(directory, path, size));
		
		return this;
	}
	
	public String end() {
		return sb.toString() + "e\n";
	}
	
	public StringBuilder builder() {
		return this.sb;
	}

}
