package com.gonzalo.taskapi.entitys;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String role;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "crreated_by")
	private UserEntity createdBy;

	@Column(name = "update_at")
	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "creatorUser", fetch = FetchType.LAZY)
	private List<PendingTaskEntity> pendingTasks;

	@OneToMany(mappedBy = "completedBy", fetch = FetchType.LAZY)
	private List<CompletedTaskEntity> completedTasks;

	@OneToMany(mappedBy = "denierUser", fetch = FetchType.LAZY)
	private List<DeniedTaskEntity> deniedTasks;

	private Double performance;

	private String position;

	@OneToMany(mappedBy = "changedBy", fetch = FetchType.LAZY)
	private List<ChangeLogEntity> changeLogsMade;

	@OneToMany(mappedBy = "requestedBy", fetch = FetchType.LAZY)
	private List<InfoRequestEntity> infoRequestMade;

	@ElementCollection
	@CollectionTable(name = "user_tools", joinColumns = @JoinColumn(name = "user_id"))
	private List<String> tools;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public UserEntity getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserEntity createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<PendingTaskEntity> getPendingTasks() {
		return pendingTasks;
	}

	public void setPendingTasks(List<PendingTaskEntity> pendingTasks) {
		this.pendingTasks = pendingTasks;
	}

	public List<CompletedTaskEntity> getCompletedTasks() {
		return completedTasks;
	}

	public void setCompletedTasks(List<CompletedTaskEntity> completedTasks) {
		this.completedTasks = completedTasks;
	}

	public List<DeniedTaskEntity> getDeniedTasks() {
		return deniedTasks;
	}

	public void setDeniedTasks(List<DeniedTaskEntity> deniedTasks) {
		this.deniedTasks = deniedTasks;
	}

	public Double getPerformance() {
		return performance;
	}

	public void setPerformance(Double performance) {
		this.performance = performance;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<String> getTools() {
		return tools;
	}

	public void setTools(List<String> tools) {
		this.tools = tools;
	}

}
