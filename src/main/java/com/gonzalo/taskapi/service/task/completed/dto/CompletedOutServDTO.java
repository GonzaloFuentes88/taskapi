package com.gonzalo.taskapi.service.task.completed.dto;

import java.time.LocalDateTime;

public class CompletedOutServDTO {

	private Long id;

	private String title;

	private String description;

	private String status;

	private LocalDateTime completedAt;

	private LocalDateTime createAt;

	private Long daysDelayed;

	private UserServOutDTO completedBy;

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

	public LocalDateTime getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public Long getDaysDelayed() {
		return daysDelayed;
	}

	public void setDaysDelayed(Long daysDelayed) {
		this.daysDelayed = daysDelayed;
	}

	public UserServOutDTO getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(UserServOutDTO completedBy) {
		this.completedBy = completedBy;
	}

}
