package com.gonzalo.taskapi.errors.exceptions;

public class TaskNotFoundException extends RuntimeException {

	public TaskNotFoundException(String message) {
		super(message);
	}

}
