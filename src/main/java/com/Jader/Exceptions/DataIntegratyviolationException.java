package com.Jader.Exceptions;

public class DataIntegratyviolationException extends RuntimeException {
	private static final long serialVersionUID=1l;
	public DataIntegratyviolationException(String message, Throwable cause) {
		super(message, cause);
		}

	public DataIntegratyviolationException(String message) {
		super(message);
		
	}

}
