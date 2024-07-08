package com.gonzalo.taskapi.service.task.pending.dto;

import java.util.List;

import com.gonzalo.taskapi.modals.PageOutObject;

public class PendingOutPageServDTO {

	private List<PendingOutServDTO> pendingList;

	private PageOutObject pageOut;

	public List<PendingOutServDTO> getPendingList() {
		return pendingList;
	}

	public void setPendingList(List<PendingOutServDTO> pendingList) {
		this.pendingList = pendingList;
	}

	public PageOutObject getPageOut() {
		return pageOut;
	}

	public void setPageOut(PageOutObject pageOut) {
		this.pageOut = pageOut;
	}

}
