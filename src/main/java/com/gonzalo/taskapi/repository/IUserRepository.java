package com.gonzalo.taskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gonzalo.taskapi.entitys.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

}
