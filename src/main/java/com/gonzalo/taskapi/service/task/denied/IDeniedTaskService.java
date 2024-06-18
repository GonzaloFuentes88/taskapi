package com.gonzalo.taskapi.service.task.denied;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gonzalo.taskapi.modals.PageInObject;
import com.gonzalo.taskapi.service.dto.UserServOutDTO;
import com.gonzalo.taskapi.service.task.denied.dto.DeniedInServDTO;
import com.gonzalo.taskapi.service.task.denied.dto.DeniedOutServDTO;

public interface IDeniedTaskService {

	DeniedOutServDTO findById(Long id);

	UserServOutDTO findDeniedUser(Long id);

	List<DeniedOutServDTO> findAll();

	List<DeniedOutServDTO> findAllByDeniedUser(Long id);

	Page<DeniedOutServDTO> findAllPage(PageInObject pageIn);

	Page<DeniedOutServDTO> findAllByDeniedUserPage(Long id, PageInObject pageIn);

	void denied(DeniedInServDTO deniedTask);

}
