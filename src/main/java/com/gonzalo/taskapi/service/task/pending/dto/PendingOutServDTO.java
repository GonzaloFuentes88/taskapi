package com.gonzalo.taskapi.service.task.pending.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.gonzalo.taskapi.service.dto.UserServOutDTO;

public class PendingOutServDTO {

	private Long id;

	private String title;

	private String description;

	private String status;

	private LocalDateTime createAt;

	private LocalDateTime dueTime;

	private Integer priorityLevel;

	private List<ChangeLogOutServDTO> changeLogsList;

	private List<InfoRequestOutServDTO> infoRequestList;

	private UserServOutDTO creatorUser;

	private UserServOutDTO assignedUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getDueTime() {
		return dueTime;
	}

	public void setDueTime(LocalDateTime dueTime) {
		this.dueTime = dueTime;
	}

	public Integer getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(Integer priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public List<ChangeLogOutServDTO> getChangeLogsList() {
		return changeLogsList;
	}

	public void setChangeLogsList(List<ChangeLogOutServDTO> changeLogsList) {
		this.changeLogsList = changeLogsList;
	}

	public List<InfoRequestOutServDTO> getInfoRequestList() {
		return infoRequestList;
	}

	public void setInfoRequestList(List<InfoRequestOutServDTO> infoRequestList) {
		this.infoRequestList = infoRequestList;
	}

	public UserServOutDTO getCreatorUser() {
		return creatorUser;
	}

	public void setCreatorUser(UserServOutDTO creatorUser) {
		this.creatorUser = creatorUser;
	}

	public UserServOutDTO getAssignedUser() {
		return assignedUser;
	}

	public void setAssignedUser(UserServOutDTO assignedUser) {
		this.assignedUser = assignedUser;
	}

}
