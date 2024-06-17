package com.gonzalo.taskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gonzalo.taskapi.modals.entitys.ChangeLogEntity;

public interface IChangeLogRepository extends JpaRepository<ChangeLogEntity, Long> {

}
