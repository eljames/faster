package org.faster.pathsize;

public class ResponseSizeDefault implements ResponseSize {

	private final String error;
	private final String size;
	
	public ResponseSizeDefault(String error, String size) {
		this.error = error;
		this.size = size;
	}
	
	@Override
	public String error() {
		return this.error;
	}

	@Override
	public String size() {
		return this.size;
	}
	
	public boolean hasError() {
		return this.size.equals("");
	}

}
