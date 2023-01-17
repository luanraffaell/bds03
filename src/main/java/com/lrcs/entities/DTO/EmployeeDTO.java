package com.lrcs.entities.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lrcs.entities.Employee;

public class EmployeeDTO {
	
	private Long id;
	
	@NotBlank
	private String name;
	@Email
	private String email;
	@NotNull
	private Long departmentId;
	
	public EmployeeDTO(Long id, String name, String email, Long departmentId) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.departmentId = departmentId;
	}
	
	public EmployeeDTO(Employee employee) {
		id = employee.getId();
		name = employee.getName();
		email = employee.getEmail();
		departmentId = employee.getDepartment().getId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getDepartment() {
		return departmentId;
	}

	public void setDepartment(Long department) {
		this.departmentId = department;
	}
	
	
}
