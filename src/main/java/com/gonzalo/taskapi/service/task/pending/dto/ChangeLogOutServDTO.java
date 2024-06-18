package com.gonzalo.taskapi.service.task.pending.dto;

import java.time.LocalDateTime;

import com.gonzalo.taskapi.service.dto.UserServOutDTO;

public class ChangeLogOutServDTO {

	private Long id;

	private LocalDateTime timestamp;

	private UserServOutDTO changedBy;

	private String changeDescription;

	private Long taskId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public UserServOutDTO getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(UserServOutDTO changedBy) {
		this.changedBy = changedBy;
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
