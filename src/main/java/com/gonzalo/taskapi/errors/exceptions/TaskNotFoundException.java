package com.gonzalo.taskapi.errors.exceptions;

public class TaskNotFoundException extends RuntimeException {

	private final String field;

	private final String method;

	public TaskNotFoundException(String message, String field, String method) {
		super(message);
		this.field = field;
		this.method = method;
	}

	public String getMethod() {
		return method;
	}

	public String getField() {
		return field;
	}

}
