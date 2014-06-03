package com.joshargent.NicePM.exceptions;

public class PlayerNotOnlineException extends Exception {
	private static final long serialVersionUID = -2146704664645365610L;
	public PlayerNotOnlineException() { super("Player specified is not online!"); }
}
