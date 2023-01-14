package com.lrcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lrcs.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
