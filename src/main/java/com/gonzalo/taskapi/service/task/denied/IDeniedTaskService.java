package com.gonzalo.taskapi.service.task.denied;

import java.util.List;
import java.util.Optional;

import com.gonzalo.taskapi.modals.entitys.DeniedTaskEntity;
import com.gonzalo.taskapi.modals.entitys.UserEntity;

public interface IDeniedTaskService {

	Optional<DeniedTaskEntity> findById(Long id);

	Optional<UserEntity> findDeniedUser(Long id);

	List<DeniedTaskEntity> findAll();

	List<DeniedTaskEntity> findAllByDenierUser(Long id);

	void denied(DeniedTaskEntity deniedTask);

}
