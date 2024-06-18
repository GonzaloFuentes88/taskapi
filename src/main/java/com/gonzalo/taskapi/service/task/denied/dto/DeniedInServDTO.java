package com.gonzalo.taskapi.service.task.denied.dto;

public class DeniedInServDTO {

	private Long taskId;

	private String reason;

	private Long deniedUserId;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getDeniedUserId() {
		return deniedUserId;
	}

	public void setDeniedUserId(Long deniedUserId) {
		this.deniedUserId = deniedUserId;
	}

}
