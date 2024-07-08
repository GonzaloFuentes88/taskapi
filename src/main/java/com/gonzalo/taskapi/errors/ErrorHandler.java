package com.gonzalo.taskapi.errors;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import com.gonzalo.taskapi.errors.dto.BadRequestMessage;
import com.gonzalo.taskapi.errors.dto.ErrorMessage;
import com.gonzalo.taskapi.errors.exceptions.TaskNotFoundException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<ErrorMessage> taskNotFound(TaskNotFoundException exception) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setField(exception.getField());
		errorMessage.setMessage(exception.getMessage());
		errorMessage.setMethod(exception.getMethod());

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorMessage> missingServletRequestParameter(MissingServletRequestParameterException ex,
			HandlerMethod handleMethod) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setField(ex.getParameterName());
		errorMessage.setMethod(handleMethod.getMethod().getName());

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<BadRequestMessage>> missingServletRequestParameter(ConstraintViolationException ex) {
		return new ResponseEntity<>(ex.getConstraintViolations().stream().map(this::mapConstraint).toList(),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorMessage> httpMessageNotReadableException(HttpMessageNotReadableException ex,
			HandlerMethod handleMethod) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage(ex.getMessage());
		errorMessage.setMethod(handleMethod.getMethod().getName());
//		errorMessage.setField(getField(ex.getCause().toString()));

		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);

	}

	private BadRequestMessage mapConstraint(ConstraintViolation<?> constraint) {
		BadRequestMessage item = new BadRequestMessage();
		Object value = constraint.getInvalidValue();
		item.setMessage(constraint.getMessage());
		item.setValidator(constraint.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName());
		item.setValue(value != null ? value.toString() : null);
		item.setField(constraint.getPropertyPath().toString().replaceAll(".+\\.(.+)", "$1"));
		return item;
	}

	private String getField(String message) {
		String field = "Unknown field";
		if (message != null) {
			int fieldIndex = message.indexOf("[\"");
			if (fieldIndex != -1) {
				int endIndex = message.indexOf("]", fieldIndex);
				if (endIndex != -1) {
					field = message.substring(fieldIndex + 2, endIndex - 1);
				}
			}
		}
		return field;
	}
}
