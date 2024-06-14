package com.gonzalo.taskapi.service.task.completed;

import java.util.List;
import java.util.Optional;

import com.gonzalo.taskapi.entitys.CompletedTaskEntity;
import com.gonzalo.taskapi.service.task.completed.dto.CompletedTaskServDTO;

public interface ICompletedTaskService {
	Optional<CompletedTaskEntity> findById(Long id) throws Exception;

	List<CompletedTaskEntity> findAllByUserId(Long id);

	void completeTask(CompletedTaskServDTO completedTaskDTO) throws Exception;
}
