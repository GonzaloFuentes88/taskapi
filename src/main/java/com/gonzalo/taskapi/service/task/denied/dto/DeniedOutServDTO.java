package com.gonzalo.taskapi.service.task.denied.dto;

import java.time.LocalDateTime;

import com.gonzalo.taskapi.service.dto.UserServOutDTO;

public class DeniedOutServDTO {

	private Long id;

	private String title;

	private String description;

	private String status;

	private LocalDateTime createAt;

	private LocalDateTime deniedAt;

	private String reason;

	private UserServOutDTO deniedUser;

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

	public LocalDateTime getDeniedAt() {
		return deniedAt;
	}

	public void setDeniedAt(LocalDateTime deniedAt) {
		this.deniedAt = deniedAt;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public UserServOutDTO getDeniedUser() {
		return deniedUser;
	}

	public void setDeniedUser(UserServOutDTO deniedUser) {
		this.deniedUser = deniedUser;
	}

}
