package com.gonzalo.taskapi.entitys;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pending_task")
public class PendingTaskEntity extends TaskEntity {

	@Column(name = "due_date")
	private LocalDateTime dueDate;

	@Column(name = "priority_level")
	private Integer priorityLevel;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
	private List<ChangeLogEntity> changeLogsList;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
	private List<InfoRequestEntity> infoRequestList;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity creatorUser;

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getPriorityLevel() {
		return priorityLevel;
	}

	public void setPriorityLevel(Integer priorityLevel) {
		this.priorityLevel = priorityLevel;
	}

	public List<ChangeLogEntity> getChangeLogsList() {
		return changeLogsList;
	}

	public void setChangeLogsList(List<ChangeLogEntity> changeLogsList) {
		this.changeLogsList = changeLogsList;
	}

	public List<InfoRequestEntity> getInfoRequestList() {
		return infoRequestList;
	}

	public void setInfoRequesList(List<InfoRequestEntity> infoRequestList) {
		this.infoRequestList = infoRequestList;
	}

	public UserEntity getCreatorUser() {
		return creatorUser;
	}

	public void setCreatorUser(UserEntity creatorUser) {
		this.creatorUser = creatorUser;
	}

	public void setInfoRequestList(List<InfoRequestEntity> infoRequestList) {
		this.infoRequestList = infoRequestList;
	}

}
