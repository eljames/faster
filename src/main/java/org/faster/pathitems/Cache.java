package org.faster.pathitems;

public class Cache<T> {
	
	private T data;
	
	public Cache(T data) {
		this.data = data;
	}
	
	public T element() {
		return this.data;
	}
	
	public T element(T data) {
		this.data = data;
		return data;
	}

}
