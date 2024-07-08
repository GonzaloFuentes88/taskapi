package com.gonzalo.taskapi.service.task.denied;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gonzalo.taskapi.errors.exceptions.TaskNotFoundException;
import com.gonzalo.taskapi.errors.exceptions.UserNotFoundException;
import com.gonzalo.taskapi.modals.PageInObject;
import com.gonzalo.taskapi.modals.entitys.DeniedTaskEntity;
import com.gonzalo.taskapi.modals.entitys.PendingTaskEntity;
import com.gonzalo.taskapi.modals.entitys.UserEntity;
import com.gonzalo.taskapi.repository.IDeniedTaskRepository;
import com.gonzalo.taskapi.repository.IPendingTaskRepository;
import com.gonzalo.taskapi.repository.IUserRepository;
import com.gonzalo.taskapi.service.ServiceExtends;
import com.gonzalo.taskapi.service.dto.UserServOutDTO;
import com.gonzalo.taskapi.service.task.denied.dto.DeniedInServDTO;
import com.gonzalo.taskapi.service.task.denied.dto.DeniedOutServDTO;
import com.gonzalo.taskapi.util.Constants;
import com.gonzalo.taskapi.util.ConstantsMessages;

@Service
public class DeniedTaskService extends ServiceExtends implements IDeniedTaskService {

	@Autowired
	private IDeniedTaskRepository deniedTaksRepository;

	@Autowired
	private IPendingTaskRepository pendingTaskRepository;

	@Autowired
	private IUserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public DeniedOutServDTO findById(Long id) {
		DeniedTaskEntity entity = deniedTaksRepository.findById(id).orElseThrow(() -> {
			String message = ConstantsMessages.TASK_NOT_FOUND.replace("{0}", Constants.TYPE_PENDING).replace("{1}",
					id.toString());
			return new TaskNotFoundException(message, "id", getMethodName());
		});
		return getDeniedOutServ(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public UserServOutDTO findDeniedUser(Long id) {

		DeniedTaskEntity entity = deniedTaksRepository.findById(id).orElseThrow(() -> {

			String message = ConstantsMessages.TASK_NOT_FOUND.replace("{0}", Constants.TYPE_PENDING).replace("{1}",
					id.toString());
			return new TaskNotFoundException(message, "id", getMethodName());
		});
		return getUserServ(entity.getDeniedUser());
	}

	@Override
	@Transactional(readOnly = true)
	public List<DeniedOutServDTO> findAll() {
		return deniedTaksRepository.findAll().stream().map(this::getDeniedOutServ).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<DeniedOutServDTO> findAllByDeniedUser(Long id) {
		return deniedTaksRepository.findAllByDeniedUserId(id).stream().map(this::getDeniedOutServ).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<DeniedOutServDTO> findAllPage(PageInObject pageIn) {
		return new PageImpl<>(
				deniedTaksRepository.findAll(preparePageable(pageIn)).stream().map(this::getDeniedOutServ).toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<DeniedOutServDTO> findAllByDeniedUserPage(Long id, PageInObject pageIn) {
		return new PageImpl<>(deniedTaksRepository.findAllByDeniedUserId(id, preparePageable(pageIn)).stream()
				.map(this::getDeniedOutServ).toList());
	}

	@Transactional()
	@Override
	public void denied(DeniedInServDTO deniedTask) {
		DeniedTaskEntity entity = new DeniedTaskEntity();
		Long pendingId = deniedTask.getTaskId();
		Long userId = deniedTask.getDeniedUserId();

		PendingTaskEntity pendingTaskDB = pendingTaskRepository.findById(pendingId).orElseThrow(() -> {
			String message = ConstantsMessages.TASK_NOT_FOUND.replace("{0}", Constants.TYPE_PENDING).replace("{1}",
					pendingId.toString());
			return new TaskNotFoundException(message, "pendingId", getMethodName());
		});

		UserEntity user = userRepository.findById(userId).orElseThrow(
				() -> new UserNotFoundException(ConstantsMessages.USER_NOT_FOUND.replace("{0}", userId.toString())));

		LocalDateTime deniedAt = LocalDateTime.now();
		entity.setTitle(pendingTaskDB.getTitle());
		entity.setDescription(pendingTaskDB.getDescription());
		entity.setDeniedAt(deniedAt);
		entity.setCreateAt(pendingTaskDB.getCreateAt());
		entity.setDeniedUser(user);
		deniedTaksRepository.save(entity);
		pendingTaskDB.setStatus(getStatusIdByValue(Constants.TYPE_DENIED));
		pendingTaskRepository.save(pendingTaskDB);

	}

}
