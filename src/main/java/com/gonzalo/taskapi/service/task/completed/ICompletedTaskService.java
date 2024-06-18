package com.gonzalo.taskapi.service.task.completed;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gonzalo.taskapi.modals.PageInObject;
import com.gonzalo.taskapi.service.dto.UserServOutDTO;
import com.gonzalo.taskapi.service.task.completed.dto.CompletedInServDTO;
import com.gonzalo.taskapi.service.task.completed.dto.CompletedOutServDTO;

public interface ICompletedTaskService {
	CompletedOutServDTO findById(Long id);

	UserServOutDTO findCompleted(Long id);

	List<CompletedOutServDTO> findAll();

	List<CompletedOutServDTO> findAllByUserId(Long id);

	Page<CompletedOutServDTO> findAllPage(PageInObject pageIn);

	Page<CompletedOutServDTO> findAllPageByUserId(Long id, PageInObject pageIn);

	void completeTask(CompletedInServDTO completedTaskDTO);
}
