package com.gonzalo.taskapi.service.task.pending;

import java.util.List;
import java.util.Optional;

import com.gonzalo.taskapi.modals.entitys.ChangeLogEntity;
import com.gonzalo.taskapi.modals.entitys.InfoRequestEntity;
import com.gonzalo.taskapi.modals.entitys.PendingTaskEntity;
import com.gonzalo.taskapi.modals.entitys.UserEntity;

public interface IPendingTaskService {

	Optional<PendingTaskEntity> findById(Long id);

	Optional<UserEntity> findCreator(Long id);

	List<PendingTaskEntity> findAll();

	List<PendingTaskEntity> findAllByCreator(Long id);

	void create(PendingTaskEntity pendingTask);

	void addChange(ChangeLogEntity change);

	void addInfo(InfoRequestEntity info);

}
