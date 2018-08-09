package org.faster.errors;

public interface Error {
	CharSequence name();
	void execute(CharSequence error);
}
