package com.gonzalo.taskapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gonzalo.taskapi.modals.entitys.PendingTaskEntity;

public interface IPendingTaskRepository extends JpaRepository<PendingTaskEntity, Long> {

	List<PendingTaskEntity> findAllByCreatorUserId(Long id);

	List<PendingTaskEntity> findAllByAssignedUserId(Long id);

	Page<PendingTaskEntity> findAllByCreatorUserId(Long id, Pageable pageable);

	Page<PendingTaskEntity> findAllByAssignedUserId(Long id, Pageable pageable);

}
