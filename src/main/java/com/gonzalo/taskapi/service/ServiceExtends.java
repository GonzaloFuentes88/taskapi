package com.gonzalo.taskapi.service;

import static java.util.Map.entry;

import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.gonzalo.taskapi.modals.PageInObject;
import com.gonzalo.taskapi.modals.entitys.CompletedTaskEntity;
import com.gonzalo.taskapi.modals.entitys.UserEntity;
import com.gonzalo.taskapi.service.task.completed.dto.CompletedOutServDTO;
import com.gonzalo.taskapi.service.task.completed.dto.UserServOutDTO;
import com.gonzalo.taskapi.util.Constants;

public class ServiceExtends {

	Map<Integer, String> statusValue = Map.ofEntries(entry(0, Constants.TYPE_PENDING),
			entry(1, Constants.TYPE_COMPLETED), entry(2, Constants.TYPE_DENIED));
	Map<String, Integer> statusKey = Map.ofEntries(entry(Constants.TYPE_PENDING, 0), entry(Constants.TYPE_COMPLETED, 1),
			entry(Constants.TYPE_DENIED, 2));

	public Pageable preparePageable(PageInObject pageIn) {
		int pageSize = pageIn.getPageSize() + 1;
		int pageNumber = pageIn.getPageNumber();

		Boolean sortCondition = (pageIn.getSort() != null && pageIn.getSort().isEmpty());
		Boolean sortValueCondition = (pageIn.getSort() != null && pageIn.getSort().isEmpty());

		return (sortCondition && sortValueCondition)
				? PageRequest.of(pageNumber, pageSize,
						Sort.by(Direction.fromString(pageIn.getSort()), pageIn.getSortField()))
				: PageRequest.of(pageNumber, pageSize);
	}

	public String getStatusValueById(Integer id) {
		return this.statusValue.get(id);
	}

	public Integer getStatusIdByValue(String value) {
		return this.statusKey.get(value);
	}

	public UserServOutDTO getUserServ(UserEntity entity) {
		UserServOutDTO outUserObj = new UserServOutDTO();
		outUserObj.setId(entity.getId());
		outUserObj.setPosition(entity.getPosition());
		outUserObj.setRole(entity.getRole());
		outUserObj.setEmail(entity.getEmail());

		return outUserObj;

	}

	public CompletedOutServDTO getCompletedOutServ(CompletedTaskEntity entity) {
		CompletedOutServDTO outObj = new CompletedOutServDTO();
		outObj.setId(entity.getId());
		outObj.setStatus(getStatusValueById(entity.getStatus()));
		outObj.setCompletedBy(getUserServ(entity.getCompletedBy()));
		outObj.setDescription(entity.getDescription());
		outObj.setDaysDelayed(entity.getDaysDelayed());
		outObj.setTitle(entity.getTitle());
		outObj.setCompletedAt(entity.getCompletedAt());

		return outObj;
	}

}
