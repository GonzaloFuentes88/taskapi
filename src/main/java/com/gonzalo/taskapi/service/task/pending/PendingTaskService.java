package com.gonzalo.taskapi.service.task.pending;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gonzalo.taskapi.errors.exceptions.TaskNotFoundException;
import com.gonzalo.taskapi.errors.exceptions.UserNotFoundException;
import com.gonzalo.taskapi.modals.PageInObject;
import com.gonzalo.taskapi.modals.PageOutObject;
import com.gonzalo.taskapi.modals.entitys.ChangeLogEntity;
import com.gonzalo.taskapi.modals.entitys.InfoRequestEntity;
import com.gonzalo.taskapi.modals.entitys.PendingTaskEntity;
import com.gonzalo.taskapi.modals.entitys.UserEntity;
import com.gonzalo.taskapi.repository.IChangeLogRepository;
import com.gonzalo.taskapi.repository.IInfoRequestRepository;
import com.gonzalo.taskapi.repository.IPendingTaskRepository;
import com.gonzalo.taskapi.repository.IUserRepository;
import com.gonzalo.taskapi.service.ServiceExtends;
import com.gonzalo.taskapi.service.dto.UserServOutDTO;
import com.gonzalo.taskapi.service.task.pending.dto.ChangeLogInServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.InfoRequestInServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.PendingInServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.PendingOutPageServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.PendingOutServDTO;
import com.gonzalo.taskapi.util.Constants;
import com.gonzalo.taskapi.util.ConstantsMessages;

@Service
public class PendingTaskService extends ServiceExtends implements IPendingTaskService {

	@Autowired
	private IPendingTaskRepository pendingTaskRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IChangeLogRepository changeRepository;

	@Autowired
	private IInfoRequestRepository infoRequestRepository;

	@Override
	@Transactional(readOnly = true)
	public PendingOutServDTO findById(Long id) {
		PendingTaskEntity taskDB = pendingTaskRepository.findById(id).orElseThrow(() -> {
			String message = ConstantsMessages.TASK_NOT_FOUND.replace("{0}", Constants.TYPE_PENDING).replace("{1}",
					id.toString());
			return new TaskNotFoundException(message, "id", getMethodName());
		});
		return getPendingOutServ(taskDB);
	}

	@Override
	@Transactional(readOnly = true)
	public UserServOutDTO findCreator(Long id) {
		// ESTE METODO DEBERIA DE BUSCAR EL CREADOR DE UNA TAREA EN ESPECIFICA

		PendingTaskEntity taskDB = pendingTaskRepository.findById(id).orElseThrow(
				() -> new UserNotFoundException(ConstantsMessages.USER_NOT_FOUND.replace("{0}", id.toString())));

		return getUserServ(taskDB.getCreatorUser());
	}

	@Override
	@Transactional(readOnly = true)
	public List<PendingOutServDTO> findAll() {
		return pendingTaskRepository.findAll().stream().map(this::getPendingOutServ).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PendingOutServDTO> findAllByCreator(Long id) {
		return pendingTaskRepository.findAllByCreatorUserId(id).stream().map(this::getPendingOutServ).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<PendingOutServDTO> findAllByAssigned(Long id) {
		return pendingTaskRepository.findAllByAssignedUserId(id).stream().map(this::getPendingOutServ).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public PendingOutPageServDTO findAllPage(PageInObject pageIn) {
		PendingOutPageServDTO outObj = new PendingOutPageServDTO();
		PageOutObject pageOut = new PageOutObject();
		Page<PendingTaskEntity> page = pendingTaskRepository.findAll(preparePageable(pageIn));
		List<PendingOutServDTO> list = page.stream().map(this::getPendingOutServ).toList();

		pageOut.setTotalPages(Long.valueOf(page.getTotalPages()));
		pageOut.setPageSize(Long.valueOf(page.getSize()));
		pageOut.setPageNumber(page.getNumber() + 1L);
		pageOut.setTotalElements(Long.valueOf(page.getTotalElements()));

		outObj.setPendingList(list);
		outObj.setPageOut(pageOut);

		return outObj;
	}

	@Override
	@Transactional(readOnly = true)
	public PendingOutPageServDTO findAllByCreatorPage(Long id, PageInObject pageIn) {
		PendingOutPageServDTO outObj = new PendingOutPageServDTO();
		PageOutObject pageOut = new PageOutObject();
		Page<PendingTaskEntity> page = pendingTaskRepository.findAllByCreatorUserId(id, preparePageable(pageIn));
		List<PendingOutServDTO> list = page.stream().map(this::getPendingOutServ).toList();

		pageOut.setTotalPages(Long.valueOf(page.getTotalPages()));
		pageOut.setPageSize(Long.valueOf(page.getSize()));
		pageOut.setPageNumber(Long.valueOf(page.getNumber()));
		pageOut.setTotalElements(Long.valueOf(page.getTotalElements()));

		outObj.setPendingList(list);
		outObj.setPageOut(pageOut);

		return outObj;
	}

	@Override
	@Transactional(readOnly = true)
	public PendingOutPageServDTO findAllByAssignedPage(Long id, PageInObject pageIn) {
		PendingOutPageServDTO outObj = new PendingOutPageServDTO();
		PageOutObject pageOut = new PageOutObject();
		Page<PendingTaskEntity> page = pendingTaskRepository.findAllByAssignedUserId(id, preparePageable(pageIn));
		List<PendingOutServDTO> list = page.stream().map(this::getPendingOutServ).toList();

		pageOut.setTotalPages(Long.valueOf(page.getTotalPages()));
		pageOut.setPageSize(Long.valueOf(page.getSize()));
		pageOut.setPageNumber(Long.valueOf(page.getNumber()));
		pageOut.setTotalElements(Long.valueOf(page.getTotalElements()));

		outObj.setPendingList(list);
		outObj.setPageOut(pageOut);

		return outObj;
	}

	@Override
	@Transactional()
	public void create(PendingInServDTO pendingTask) {
		PendingTaskEntity entity = new PendingTaskEntity();
		Long creatorUserId = pendingTask.getCreatorUserId();
		Long assignedUserId = pendingTask.getAssignedUserId();

		UserEntity creatorUserDB = userRepository.findById(creatorUserId).orElseThrow(() -> new UserNotFoundException(
				ConstantsMessages.CREATOR_USER_NOT_FOUND.replace("{0}", creatorUserId.toString())));

		UserEntity assignedUserDB = userRepository.findById(assignedUserId).orElseThrow(() -> new UserNotFoundException(
				ConstantsMessages.ASSIGNED_USER_NOT_FOUND.replace("{0}", creatorUserId.toString())));

		entity.setTitle(pendingTask.getTitle());
		entity.setDescription(pendingTask.getDescription());
		entity.setStatus(getStatusIdByValue(Constants.TYPE_PENDING));
		entity.setCreateAt(LocalDateTime.now());
		entity.setDueDate(pendingTask.getDueDate());
		entity.setPriorityLevel(pendingTask.getPriorityLevel());
		entity.setCreatorUser(creatorUserDB);
		entity.setAssignedUser(assignedUserDB);

		pendingTaskRepository.save(entity);
	}

	@Override
	@Transactional()
	public void addChange(ChangeLogInServDTO change) {
		ChangeLogEntity entity = new ChangeLogEntity();
		Long taskId = change.getTaskId();
		Long changedById = change.getChangedById();

		PendingTaskEntity taskDB = pendingTaskRepository.findById(taskId).orElseThrow(() -> {
			String message = ConstantsMessages.TASK_NOT_FOUND.replace("{0}", Constants.TYPE_PENDING).replace("{1}",
					taskId.toString());
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			return new TaskNotFoundException(message, "id", getMethodName());
		});

		UserEntity creatorUserDB = userRepository.findById(changedById).orElseThrow(() -> new UserNotFoundException(
				ConstantsMessages.USER_NOT_FOUND.replace("{0}", changedById.toString())));

		entity.setChangeDescription(change.getChangeDescription());
		entity.setTimestamp(LocalDateTime.now());
		entity.setChangedBy(creatorUserDB);
		entity.setTask(taskDB);

		changeRepository.save(entity);

	}

	@Override
	@Transactional()
	public void addInfo(InfoRequestInServDTO info) {
		InfoRequestEntity entity = new InfoRequestEntity();
		Long taskId = info.getTaskId();
		Long infoRequestById = info.getRequestedById();

		PendingTaskEntity taskDB = pendingTaskRepository.findById(taskId).orElseThrow(() -> {
			String message = ConstantsMessages.TASK_NOT_FOUND.replace("{0}", Constants.TYPE_PENDING).replace("{1}",
					taskId.toString());
			String methodName = new Object() {
			}.getClass().getEnclosingMethod().getName();
			return new TaskNotFoundException(message, "id", getMethodName());
		});

		UserEntity requestByUserDB = userRepository.findById(infoRequestById)
				.orElseThrow(() -> new UserNotFoundException(
						ConstantsMessages.USER_NOT_FOUND.replace("{0}", infoRequestById.toString())));

		entity.setMessage(info.getMessage());
		entity.setTimestamp(LocalDateTime.now());
		entity.setRequestedBy(requestByUserDB);
		entity.setTask(taskDB);

		infoRequestRepository.save(entity);
	}

}
