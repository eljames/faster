package org.faster.errors;

public interface HandledError {
	void handle(CharSequence error, CharSequence path);
}
