package com.gonzalo.taskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gonzalo.taskapi.modals.entitys.DeniedTaskEntity;

public interface IDeniedTaskRepository extends JpaRepository<DeniedTaskEntity, Long> {

}
