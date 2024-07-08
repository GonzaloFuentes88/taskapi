package com.gonzalo.taskapi.endpoints.pendingtask.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AddChangePendingInDTO {

	@Min(1)
	@NotNull
	private Long changedBy;

	@NotEmpty
	@Length(max = 255)
	private String changeDescription;

	@Min(1)
	@NotNull
	private Long pendingId;

	public Long getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(Long changedBy) {
		this.changedBy = changedBy;
	}

	public String getChangeDescription() {
		return changeDescription;
	}

	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}

	public Long getPendingId() {
		return pendingId;
	}

	public void setPendingId(Long pendingId) {
		this.pendingId = pendingId;
	}

}
