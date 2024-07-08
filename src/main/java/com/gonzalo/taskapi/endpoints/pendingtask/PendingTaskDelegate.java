package com.gonzalo.taskapi.endpoints.pendingtask;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.gonzalo.taskapi.endpoints.pendingtask.dto.AddChangePendingInDTO;
import com.gonzalo.taskapi.endpoints.pendingtask.dto.AddInfoPendingInDTO;
import com.gonzalo.taskapi.endpoints.pendingtask.dto.CreatePendingInDTO;
import com.gonzalo.taskapi.modals.PageInObject;
import com.gonzalo.taskapi.service.dto.UserServOutDTO;
import com.gonzalo.taskapi.service.task.pending.IPendingTaskService;
import com.gonzalo.taskapi.service.task.pending.dto.ChangeLogInServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.InfoRequestInServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.PendingInServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.PendingOutPageServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.PendingOutServDTO;

import jakarta.validation.Valid;

@Component
public class PendingTaskDelegate {

	@Autowired
	private IPendingTaskService pendingTaskService;

	ResponseEntity<PendingOutServDTO> getPendingTask(int id) {
		return ResponseEntity.ok(pendingTaskService.findById(Long.valueOf(id)));
	}

	ResponseEntity<UserServOutDTO> getCreatorPending(int id) {
		return ResponseEntity.ok(pendingTaskService.findCreator(Long.valueOf(id)));
	}

	ResponseEntity<List<PendingOutServDTO>> getAllPending() {
		return ResponseEntity.ok(pendingTaskService.findAll());
	}

	ResponseEntity<List<PendingOutServDTO>> getAllPendingByCreator(int id) {
		return ResponseEntity.ok(pendingTaskService.findAllByCreator(Long.valueOf(id)));
	}

	ResponseEntity<PendingOutPageServDTO> getAllPagePending(Integer pageSize, Integer pageNumber, String sort,
			String sortField) {
		PageInObject pageIn = new PageInObject();
		pageIn.setPageNumber(pageNumber);
		pageIn.setPageSize(pageSize);
		pageIn.setSort(sort);
		pageIn.setSortField(sortField);
		return ResponseEntity.ok(pendingTaskService.findAllPage(pageIn));
	}

	ResponseEntity<PendingOutPageServDTO> getAllPagePendingByCreator(Integer pageSize, Integer pageNumber, String sort,
			String sortField, Integer idCreator) {
		PageInObject pageIn = new PageInObject();
		pageIn.setPageNumber(pageNumber);
		pageIn.setPageSize(pageSize);
		pageIn.setSort(sort);
		pageIn.setSortField(sortField);
		return ResponseEntity.ok(pendingTaskService.findAllByCreatorPage(Long.valueOf(idCreator), pageIn));
	}

	ResponseEntity<PendingOutPageServDTO> getAllPagePendingByAssigned(Integer pageSize, Integer pageNumber, String sort,
			String sortField, Integer idAssigned) {
		PageInObject pageIn = new PageInObject();
		pageIn.setPageNumber(pageNumber);
		pageIn.setPageSize(pageSize);
		pageIn.setSort(sort);
		pageIn.setSortField(sortField);
		return ResponseEntity.ok(pendingTaskService.findAllByAssignedPage(Long.valueOf(idAssigned), pageIn));
	}

	ResponseEntity<Void> createPending(CreatePendingInDTO inObj) {
		PendingInServDTO pendingServ = new PendingInServDTO();

		pendingServ.setAssignedUserId(inObj.getAssignedUserId());
		pendingServ.setCreatorUserId(inObj.getCreatorUserId());
		pendingServ.setDescription(inObj.getDescription());
		pendingServ.setDueDate(inObj.getDueDate());
		pendingServ.setPriorityLevel(inObj.getPriorityLevel());
		pendingServ.setTitle(inObj.getTitle());

		pendingTaskService.create(pendingServ);
		return ResponseEntity.created(null).build();
	}

	ResponseEntity<Void> addChangePending(AddChangePendingInDTO inObj) {
		ChangeLogInServDTO changePendingServ = new ChangeLogInServDTO();

		changePendingServ.setChangedById(inObj.getChangedBy());
		changePendingServ.setChangeDescription(inObj.getChangeDescription());
		changePendingServ.setTaskId(inObj.getPendingId());

		pendingTaskService.addChange(changePendingServ);
		return ResponseEntity.noContent().build();
	}

	ResponseEntity<Void> addInfoPending(@Valid AddInfoPendingInDTO inObj) {
		InfoRequestInServDTO infoPendingServ = new InfoRequestInServDTO();

		infoPendingServ.setMessage(inObj.getMessage());
		infoPendingServ.setRequestedById(inObj.getRequestedBy());
		infoPendingServ.setTaskId(inObj.getPendingId());

		pendingTaskService.addInfo(infoPendingServ);

		return ResponseEntity.noContent().build();
	}

}
