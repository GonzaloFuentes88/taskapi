package com.gonzalo.taskapi.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gonzalo.taskapi.modals.entitys.CompletedTaskEntity;

public interface ICompletedTaskRepository extends JpaRepository<CompletedTaskEntity, Long> {

	List<CompletedTaskEntity> findAllByCompletedBy(Long id);

	List<CompletedTaskEntity> findAllByCompletedBy(Long id, Pageable pageable);
}
