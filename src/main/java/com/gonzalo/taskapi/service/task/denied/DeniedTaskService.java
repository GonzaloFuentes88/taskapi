package com.gonzalo.taskapi.service.task.denied;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gonzalo.taskapi.entitys.DeniedTaskEntity;
import com.gonzalo.taskapi.entitys.UserEntity;

@Service
public class DeniedTaskService implements IDeniedTaskService {

	@Override
	public Optional<DeniedTaskEntity> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<UserEntity> findDeniedUser(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<DeniedTaskEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DeniedTaskEntity> findAllByDenierUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void denied(DeniedTaskEntity deniedTask) {
		// TODO Auto-generated method stub

	}

}
