package com.gonzalo.taskapi;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gonzalo.taskapi.service.task.pending.IPendingTaskService;
import com.gonzalo.taskapi.service.task.pending.dto.PendingInServDTO;
import com.gonzalo.taskapi.service.task.pending.dto.PendingOutServDTO;

@SpringBootTest
class PendingTaskServiceTest {

	@Autowired
	private IPendingTaskService pendingService;

	@Test
	void testCreate() {
		PendingInServDTO obj = new PendingInServDTO();
		obj.setTitle("Test1");
		obj.setPriorityLevel(5);
		obj.setDescription("Test1");
		obj.setDueDate(LocalDateTime.parse("2024-06-19T10:15:30"));
		obj.setCreatorUserId(1L);
		obj.setAssignedUserId(2L);

		pendingService.create(obj);

		List<PendingOutServDTO> list = pendingService.findAll();
		PendingOutServDTO objReturn = list.get(list.size() - 1);

		assertAll(() -> assertEquals("Test1", objReturn.getTitle()),
				() -> assertEquals(5, objReturn.getPriorityLevel()),
				() -> assertEquals("Test1", objReturn.getDescription()),
				() -> assertEquals(LocalDateTime.parse("2024-06-19T10:15:30"), objReturn.getDueTime()),
				() -> assertEquals(1L, objReturn.getCreatorUser().getId()),
				() -> assertEquals(2L, objReturn.getAssignedUser().getId()));

	}

}
