package org.faster.errors;

public class HandledErrorNothing implements HandledError {

	@Override
	public void handle(CharSequence error, CharSequence path) { /* Nothing */ }

}
