package com.gonzalo.taskapi.service.task.completed.dto;

public class CompletedInServDTO {

	private Long pendingTaskId;

	private Long userCompletedId;

	public Long getPendingTaskId() {
		return pendingTaskId;
	}

	public void setPendingTaskId(Long pendingTaskId) {
		this.pendingTaskId = pendingTaskId;
	}

	public Long getUserCompletedId() {
		return userCompletedId;
	}

	public void setUserCompletedId(Long userCompletedId) {
		this.userCompletedId = userCompletedId;
	}

}
