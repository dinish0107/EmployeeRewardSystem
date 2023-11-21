package com.springboot.main.exception;

public class InvalidIdException extends Exception {

	private static final long serialVersionUID = 3721472810253966615L;
	private String message;

	public InvalidIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
