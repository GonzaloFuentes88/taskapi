package com.gonzalo.taskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gonzalo.taskapi.entitys.PendingTaskEntity;

public interface IPendingTaskRepository extends JpaRepository<PendingTaskEntity, Long> {

}
