package com.gonzalo.taskapi.service.task.completed;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gonzalo.taskapi.entitys.CompletedTaskEntity;
import com.gonzalo.taskapi.entitys.PendingTaskEntity;
import com.gonzalo.taskapi.entitys.UserEntity;
import com.gonzalo.taskapi.errors.exceptions.TaskNotFoundException;
import com.gonzalo.taskapi.errors.exceptions.UserNotFoundException;
import com.gonzalo.taskapi.repository.ICompletedTaskRepository;
import com.gonzalo.taskapi.repository.IPendingTaskRepository;
import com.gonzalo.taskapi.repository.IUserRepository;
import com.gonzalo.taskapi.service.task.completed.dto.CompletedTaskServDTO;
import com.gonzalo.taskapi.util.Constants;
import com.gonzalo.taskapi.util.ConstantsMessages;

@Service
public class CompletedTaskService implements ICompletedTaskService {

	@Autowired
	private ICompletedTaskRepository completedTaskRepository;

	@Autowired
	private IPendingTaskRepository pendingTaskRepository;

	@Autowired
	private IUserRepository userRepository;

	@Override
	public Optional<CompletedTaskEntity> findById(Long id) throws Exception {

		return Optional.ofNullable(completedTaskRepository.findById(id)
				.orElseThrow(() -> new TaskNotFoundException(ConstantsMessages.TASK_NOT_FOUND
						.replace("{0}", Constants.TYPE_COMPLETED).replace("{1}", id.toString()))));
	}

	@Override
	public List<CompletedTaskEntity> findAllByUserId(Long id) {
		return completedTaskRepository.findAllByCompletedBy(id);
	}

	@Override
	public void completeTask(CompletedTaskServDTO completedTask) throws Exception {
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

	}

}
