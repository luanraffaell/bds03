package com.lrcs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lrcs.entities.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	Optional<Department> findByName(String name);
}
