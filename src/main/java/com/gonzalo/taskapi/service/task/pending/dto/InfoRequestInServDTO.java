package com.gonzalo.taskapi.service.task.pending.dto;

public class InfoRequestInServDTO {

	private Long requestedById;

	private String message;

	private Long taskId;

	public Long getRequestedById() {
		return requestedById;
	}

	public void setRequestedById(Long requestedById) {
		this.requestedById = requestedById;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

}
