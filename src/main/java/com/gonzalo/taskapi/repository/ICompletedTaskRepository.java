package com.gonzalo.taskapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gonzalo.taskapi.entitys.CompletedTaskEntity;

public interface ICompletedTaskRepository extends JpaRepository<CompletedTaskEntity, Long> {

	List<CompletedTaskEntity> findAllByCompletedBy(Long id);
}
