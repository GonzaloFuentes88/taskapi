package com.gonzalo.taskapi.service.task.pending;

import java.util.List;

import com.gonzalo.taskapi.modals.PageInObject;
import com.gonzalo.taskapi.service.dto.UserServOutDTO;
import com.gonzalo.taskapi.service.task.pending.dto.ChangeLogInServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.InfoRequestInServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.PendingInServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.PendingOutPageServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.PendingOutServDTO;

public interface IPendingTaskService {

	PendingOutServDTO findById(Long id);

	UserServOutDTO findCreator(Long id);

	List<PendingOutServDTO> findAll();

	List<PendingOutServDTO> findAllByCreator(Long id);

	List<PendingOutServDTO> findAllByAssigned(Long id);

	PendingOutPageServDTO findAllPage(PageInObject pageIn);

	PendingOutPageServDTO findAllByCreatorPage(Long id, PageInObject pageIn);

	PendingOutPageServDTO findAllByAssignedPage(Long id, PageInObject pageIn);

	void create(PendingInServDTO pendingTask);

	void addChange(ChangeLogInServDTO change);

	void addInfo(InfoRequestInServDTO info);

}
