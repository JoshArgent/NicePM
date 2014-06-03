package com.joshargent.NicePM.exceptions;

public class SelfMessageException extends Exception {
	private static final long serialVersionUID = 7556454315491149941L;
	public SelfMessageException() { super("Can not send messages to yourself!"); }
}
