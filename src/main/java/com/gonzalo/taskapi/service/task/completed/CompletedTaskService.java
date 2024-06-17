package com.gonzalo.taskapi.service.task.completed;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.gonzalo.taskapi.errors.exceptions.TaskNotFoundException;
import com.gonzalo.taskapi.errors.exceptions.UserNotFoundException;
import com.gonzalo.taskapi.modals.PageInObject;
import com.gonzalo.taskapi.modals.entitys.CompletedTaskEntity;
import com.gonzalo.taskapi.modals.entitys.PendingTaskEntity;
import com.gonzalo.taskapi.modals.entitys.UserEntity;
import com.gonzalo.taskapi.repository.ICompletedTaskRepository;
import com.gonzalo.taskapi.repository.IPendingTaskRepository;
import com.gonzalo.taskapi.repository.IUserRepository;
import com.gonzalo.taskapi.service.ServiceExtends;
import com.gonzalo.taskapi.service.task.completed.dto.CompletedInServDTO;
import com.gonzalo.taskapi.service.task.completed.dto.CompletedOutServDTO;
import com.gonzalo.taskapi.service.task.completed.dto.UserServOutDTO;
import com.gonzalo.taskapi.util.Constants;
import com.gonzalo.taskapi.util.ConstantsMessages;

@Service
public class CompletedTaskService extends ServiceExtends implements ICompletedTaskService {

	@Autowired
	private ICompletedTaskRepository completedTaskRepository;

	@Autowired
	private IPendingTaskRepository pendingTaskRepository;

	@Autowired
	private IUserRepository userRepository;

	@Override
	public CompletedOutServDTO findById(Long id) throws Exception {

		CompletedTaskEntity taskDB = completedTaskRepository.findById(id)
				.orElseThrow(() -> new TaskNotFoundException(ConstantsMessages.TASK_NOT_FOUND
						.replace("{0}", Constants.TYPE_COMPLETED).replace("{1}", id.toString())));

		return getCompletedOutServ(taskDB);
	}

	@Override
	public List<CompletedOutServDTO> findAllByUserId(Long id) {
		return completedTaskRepository.findAllByCompletedBy(id).stream().map(this::getCompletedOutServ).toList();
	}

	@Override
	public List<CompletedOutServDTO> findAll() {
		return completedTaskRepository.findAll().stream().map(this::getCompletedOutServ).toList();
	}

	@Override
	public Page<CompletedOutServDTO> findAllPage(PageInObject pageIn) {
		return new PageImpl<>(completedTaskRepository.findAll(preparePageable(pageIn)).stream()
				.map(this::getCompletedOutServ).toList());
	}

	@Override
	public Page<CompletedOutServDTO> findAllPageByUserId(Long id, PageInObject pageIn) {
		return new PageImpl<>(completedTaskRepository.findAllByCompletedBy(id, preparePageable(pageIn)).stream()
				.map(this::getCompletedOutServ).toList());
	}

	@Override
	public void completeTask(CompletedInServDTO completedTask) throws Exception {
		CompletedTaskEntity completedTaskDB = new CompletedTaskEntity();

		PendingTaskEntity pendingTask = pendingTaskRepository.findById(completedTask.getPendingTaskId()).orElseThrow(
				() -> new TaskNotFoundException(ConstantsMessages.TASK_NOT_FOUND.replace("{0}", Constants.TYPE_PENDING)
						.replace("{1}", completedTask.getPendingTaskId().toString())));

		UserEntity user = userRepository.findById(completedTask.getUserCompletedId())
				.orElseThrow(() -> new UserNotFoundException(ConstantsMessages.USER_NOT_FOUND.replace("{0}",
						completedTask.getUserCompletedId().toString())));

		LocalDateTime completedAt = LocalDateTime.now();
		LocalDateTime dueAt = pendingTask.getDueDate();
		Long daysDelayed = ChronoUnit.DAYS.between(dueAt, completedAt);
		completedTaskDB.setTitle(pendingTask.getTitle());
		completedTaskDB.setDescription(pendingTask.getDescription());
		completedTaskDB.setCompletedAt(completedAt);
		completedTaskDB.setDaysDelayed(daysDelayed);
		completedTaskDB.setCreateAt(pendingTask.getCreateAt());
		completedTaskDB.setCompletedBy(user);
		completedTaskRepository.save(completedTaskDB);
		pendingTask.setStatus(getStatusIdByValue(Constants.TYPE_COMPLETED));
		pendingTaskRepository.save(pendingTask);

	}

	private UserServOutDTO getUserServ(UserEntity entity) {
		UserServOutDTO outUserObj = new UserServOutDTO();
		outUserObj.setId(entity.getId());
		outUserObj.setPosition(entity.getPosition());
		outUserObj.setRole(entity.getRole());
		outUserObj.setEmail(entity.getEmail());

		return outUserObj;

	}

	private CompletedOutServDTO getCompletedOutServ(CompletedTaskEntity entity) {
		CompletedOutServDTO outObj = new CompletedOutServDTO();
		outObj.setId(entity.getId());
		outObj.setStatus(getStatusValueById(entity.getStatus()));
		outObj.setCompletedBy(getUserServ(entity.getCompletedBy()));
		outObj.setDescription(entity.getDescription());
		outObj.setDaysDelayed(entity.getDaysDelayed());
		outObj.setTitle(entity.getTitle());
		outObj.setCompletedAt(entity.getCompletedAt());

		return outObj;
	}

}
