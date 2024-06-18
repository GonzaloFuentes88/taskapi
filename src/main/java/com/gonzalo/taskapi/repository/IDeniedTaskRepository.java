package com.gonzalo.taskapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gonzalo.taskapi.modals.entitys.DeniedTaskEntity;

public interface IDeniedTaskRepository extends JpaRepository<DeniedTaskEntity, Long> {
	Optional<DeniedTaskEntity> findByDeniedUserId(Long id);

	List<DeniedTaskEntity> findAllByDeniedUserId(Long id);

	Page<DeniedTaskEntity> findAllByDeniedUserId(Long id, Pageable pageable);
}
