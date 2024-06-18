package com.gonzalo.taskapi.modals.entitys;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "denied_tasks")
public class DeniedTaskEntity extends TaskEntity {

	@Column(name = "denied_at")
	private LocalDateTime deniedAt;

	private String reason;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity deniedUser;

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

	public UserEntity getDeniedUser() {
		return deniedUser;
	}

	public void setDeniedUser(UserEntity deniedUser) {
		this.deniedUser = deniedUser;
	}

}
