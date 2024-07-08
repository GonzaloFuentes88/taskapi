package com.gonzalo.taskapi.service.task.pending.dto;

public class ChangeLogInServDTO {

	private Long changedById;

	private String changeDescription;

	private Long taskId;

	public Long getChangedById() {
		return changedById;
	}

	public void setChangedById(Long changedById) {
		this.changedById = changedById;
	}

	public String getChangeDescription() {
		return changeDescription;
	}

	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

}
