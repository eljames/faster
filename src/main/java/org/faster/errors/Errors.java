package org.faster.errors;

public interface Errors {
	Errors add(Error error);
	void handle(CharSequence error, CharSequence path);
}
