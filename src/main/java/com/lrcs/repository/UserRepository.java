package com.lrcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lrcs.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}