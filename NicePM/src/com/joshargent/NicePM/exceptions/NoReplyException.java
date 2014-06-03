package com.joshargent.NicePM.exceptions;

public class NoReplyException extends Exception {
	private static final long serialVersionUID = -1323912296147707794L;
	public NoReplyException() { super("You don't have any message to reply to!"); }
}
