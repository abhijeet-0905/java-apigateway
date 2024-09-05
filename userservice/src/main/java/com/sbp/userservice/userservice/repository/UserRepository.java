package com.sbp.userservice.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbp.userservice.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
