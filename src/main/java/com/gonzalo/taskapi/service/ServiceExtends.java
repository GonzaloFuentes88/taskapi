package com.gonzalo.taskapi.service;

import static java.util.Map.entry;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.gonzalo.taskapi.modals.PageInObject;
import com.gonzalo.taskapi.modals.entitys.ChangeLogEntity;
import com.gonzalo.taskapi.modals.entitys.CompletedTaskEntity;
import com.gonzalo.taskapi.modals.entitys.DeniedTaskEntity;
import com.gonzalo.taskapi.modals.entitys.InfoRequestEntity;
import com.gonzalo.taskapi.modals.entitys.PendingTaskEntity;
import com.gonzalo.taskapi.modals.entitys.UserEntity;
import com.gonzalo.taskapi.service.dto.UserServOutDTO;
import com.gonzalo.taskapi.service.task.completed.dto.CompletedOutServDTO;
import com.gonzalo.taskapi.service.task.denied.dto.DeniedOutServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.ChangeLogOutServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.InfoRequestOutServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.PendingOutServDTO;
import com.gonzalo.taskapi.util.Constants;

public class ServiceExtends {

	Map<Integer, String> statusValue = Map.ofEntries(entry(0, Constants.TYPE_PENDING),
			entry(1, Constants.TYPE_COMPLETED), entry(2, Constants.TYPE_DENIED));

	Map<String, Integer> statusKey = Map.ofEntries(entry(Constants.TYPE_PENDING, 0), entry(Constants.TYPE_COMPLETED, 1),
			entry(Constants.TYPE_DENIED, 2));

	public Pageable preparePageable(PageInObject pageIn) {
		int pageSize = pageIn.getPageSize();
		int pageNumber = pageIn.getPageNumber() - 1;

		Boolean sortCondition = (pageIn.getSort() != null && pageIn.getSort().isEmpty());
		Boolean sortValueCondition = (pageIn.getSort() != null && pageIn.getSort().isEmpty());

		return (sortCondition && sortValueCondition)
				? PageRequest.of(pageNumber, pageSize,
						Sort.by(Direction.fromString(pageIn.getSort()), pageIn.getSortField()))
				: PageRequest.of(pageNumber, pageSize);
	}

	public String getStatusValueById(Integer id) {
		return this.statusValue.get(id);
	}

	public Integer getStatusIdByValue(String value) {
		return this.statusKey.get(value);
	}

	public UserServOutDTO getUserServ(UserEntity entity) {
		UserServOutDTO outUserObj = new UserServOutDTO();
		outUserObj.setId(entity.getId());
		outUserObj.setPosition(entity.getPosition());
		outUserObj.setRole(entity.getRole());
		outUserObj.setEmail(entity.getEmail());

		return outUserObj;

	}

	public CompletedOutServDTO getCompletedOutServ(CompletedTaskEntity entity) {
		CompletedOutServDTO outObj = new CompletedOutServDTO();
		outObj.setId(entity.getId());
		outObj.setStatus(getStatusValueById(entity.getStatus()));
		outObj.setCompletedBy(getUserServ(entity.getCompletedBy()));
		outObj.setDescription(entity.getDescription());
		outObj.setCreateAt(entity.getCreateAt());
		outObj.setDaysDelayed(entity.getDaysDelayed());
		outObj.setTitle(entity.getTitle());
		outObj.setCompletedAt(entity.getCompletedAt());

		return outObj;
	}

	public PendingOutServDTO getPendingOutServ(PendingTaskEntity entity) {
		PendingOutServDTO outObj = new PendingOutServDTO();
		outObj.setId(entity.getId());
		outObj.setTitle(entity.getTitle());
		outObj.setDescription(entity.getDescription());
		outObj.setStatus(getStatusValueById(entity.getStatus()));
		outObj.setCreateAt(entity.getCreateAt());
		outObj.setDueTime(entity.getDueDate());
		outObj.setPriorityLevel(entity.getPriorityLevel());
		outObj.setCreatorUser(getUserServ(entity.getCreatorUser()));
		outObj.setAssignedUser(getUserServ(entity.getAssignedUser()));
		outObj.setChangeLogsList(entity.getChangeLogsList().stream().map(this::getChangeLog).toList());
		outObj.setInfoRequestList(entity.getInfoRequestList().stream().map(this::getInfoRequest).toList());

		return outObj;
	}

	public DeniedOutServDTO getDeniedOutServ(DeniedTaskEntity entity) {
		DeniedOutServDTO outObj = new DeniedOutServDTO();
		outObj.setId(entity.getId());
		outObj.setTitle(entity.getTitle());
		outObj.setDescription(entity.getDescription());
		outObj.setStatus(getStatusValueById(entity.getStatus()));
		outObj.setCreateAt(entity.getCreateAt());
		outObj.setDeniedAt(entity.getDeniedAt());
		outObj.setReason(entity.getReason());
		outObj.setDeniedUser(getUserServ(entity.getDeniedUser()));

		return outObj;
	}

	private ChangeLogOutServDTO getChangeLog(ChangeLogEntity changeLogDB) {
		ChangeLogOutServDTO outObj = new ChangeLogOutServDTO();
		outObj.setId(changeLogDB.getId());
		outObj.setChangeDescription(changeLogDB.getChangeDescription());
		outObj.setChangedBy(getUserServ(changeLogDB.getChangedBy()));
		outObj.setTaskId(changeLogDB.getTask().getId());
		outObj.setTimestamp(changeLogDB.getTimestamp());

		return outObj;
	}

	private InfoRequestOutServDTO getInfoRequest(InfoRequestEntity infoRequestDB) {
		InfoRequestOutServDTO outObj = new InfoRequestOutServDTO();
		outObj.setId(infoRequestDB.getId());
		outObj.setMessage(infoRequestDB.getMessage());
		outObj.setRequestedBy(getUserServ(infoRequestDB.getRequestedBy()));
		outObj.setTaskId(infoRequestDB.getTask().getId());
		outObj.setTimestamp(infoRequestDB.getTimestamp());

		return outObj;
	}

	public String getMethodName() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		return stackTrace[4].getMethodName();
	}

}
