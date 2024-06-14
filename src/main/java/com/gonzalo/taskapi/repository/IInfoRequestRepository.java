package com.gonzalo.taskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gonzalo.taskapi.entitys.InfoRequestEntity;

public interface IInfoRequestRepository extends JpaRepository<InfoRequestEntity, Long> {

}
