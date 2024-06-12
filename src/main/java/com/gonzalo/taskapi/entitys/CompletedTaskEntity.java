package com.gonzalo.taskapi.entitys;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "completed_tasks")
public class CompletedTaskEntity extends TaskEntity {

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "days_delayed")
	private Long daysDelayed;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity completedBy;

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Long getDaysDelayed() {
		return daysDelayed;
	}

	public void setDaysDelayed(Long daysDelayed) {
		this.daysDelayed = daysDelayed;
	}

	public UserEntity getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(UserEntity completedBy) {
		this.completedBy = completedBy;
	}

}
