package com.lrcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lrcs.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
