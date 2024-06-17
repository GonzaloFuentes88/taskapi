package com.gonzalo.taskapi.service.task.pending;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gonzalo.taskapi.modals.entitys.ChangeLogEntity;
import com.gonzalo.taskapi.modals.entitys.InfoRequestEntity;
import com.gonzalo.taskapi.modals.entitys.PendingTaskEntity;
import com.gonzalo.taskapi.modals.entitys.UserEntity;

@Service
public class PendingTaskService implements IPendingTaskService {

	@Override
	public Optional<PendingTaskEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<UserEntity> findCreator(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<PendingTaskEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PendingTaskEntity> findAllByCreator(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(PendingTaskEntity pendingTask) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addChange(ChangeLogEntity change) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addInfo(InfoRequestEntity info) {
		// TODO Auto-generated method stub

	}

}
