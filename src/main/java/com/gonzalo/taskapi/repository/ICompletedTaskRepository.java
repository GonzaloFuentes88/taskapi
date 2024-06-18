package com.gonzalo.taskapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gonzalo.taskapi.modals.entitys.CompletedTaskEntity;

public interface ICompletedTaskRepository extends JpaRepository<CompletedTaskEntity, Long> {

	Optional<CompletedTaskEntity> findByCompletedById(Long id);

	List<CompletedTaskEntity> findAllByCompletedById(Long id);

	Page<CompletedTaskEntity> findAllByCompletedById(Long id, Pageable pageable);

}
