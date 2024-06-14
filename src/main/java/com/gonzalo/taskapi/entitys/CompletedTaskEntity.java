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

	@Column(name = "completed_at")
	private LocalDateTime completedAt;

	@Column(name = "days_delayed")
	private Long daysDelayed;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity completedBy;

	public LocalDateTime getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
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
