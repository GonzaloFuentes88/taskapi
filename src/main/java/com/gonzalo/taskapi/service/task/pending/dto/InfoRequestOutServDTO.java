package com.gonzalo.taskapi.service.task.pending.dto;

import java.time.LocalDateTime;

import com.gonzalo.taskapi.service.dto.UserServOutDTO;

public class InfoRequestOutServDTO {

	private Long id;

	private UserServOutDTO requestedBy;

	private String message;

	private LocalDateTime timestamp;

	private Long taskId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserServOutDTO getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(UserServOutDTO requestedBy) {
		this.requestedBy = requestedBy;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

}
