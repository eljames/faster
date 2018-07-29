package org.faster.errors;

public class ErsNothing implements Errors {

	@Override
	public Errors add(Error error) {
		return this;
	}

	@Override
	public void handle(CharSequence error, CharSequence path) { }

}
