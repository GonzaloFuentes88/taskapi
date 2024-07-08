package com.gonzalo.taskapi.endpoints.pendingtask.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AddInfoPendingInDTO {

	@Min(1)
	@NotNull
	private Long requestedBy;

	@NotEmpty
	@Length(max = 255)
	private String message;

	@Min(1)
	@NotNull
	private Long pendingId;

	public Long getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(Long requestedBy) {
		this.requestedBy = requestedBy;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getPendingId() {
		return pendingId;
	}

	public void setPendingId(Long pendingId) {
		this.pendingId = pendingId;
	}

}
