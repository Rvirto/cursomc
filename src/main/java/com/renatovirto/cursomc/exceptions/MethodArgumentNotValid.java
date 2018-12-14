package com.renatovirto.cursomc.exceptions;

public class MethodArgumentNotValid extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MethodArgumentNotValid(String msg) {
		super(msg);
	}
	
	public MethodArgumentNotValid(String msg, Throwable cause) {
		super(msg, cause);
	}
}
