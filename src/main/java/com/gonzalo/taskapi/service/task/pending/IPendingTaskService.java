package com.gonzalo.taskapi.service.task.pending;

import java.util.List;
import java.util.Optional;

import com.gonzalo.taskapi.entitys.ChangeLogEntity;
import com.gonzalo.taskapi.entitys.InfoRequestEntity;
import com.gonzalo.taskapi.entitys.PendingTaskEntity;
import com.gonzalo.taskapi.entitys.UserEntity;

public interface IPendingTaskService {

	Optional<PendingTaskEntity> findById(Long id);

	Optional<UserEntity> findCreator(Long id);

	List<PendingTaskEntity> findAll();

	List<PendingTaskEntity> findAllByCreator(Long id);

	void create(PendingTaskEntity pendingTask);

	void addChange(ChangeLogEntity change);

	void addInfo(InfoRequestEntity info);

}
