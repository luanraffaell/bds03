package com.lrcs.entities.DTO;

import com.lrcs.entities.Employee;

public class EmployeeDTO {
	
	private Long id;
	private String name;
	private String email;
	
	public EmployeeDTO(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public EmployeeDTO(Employee employee) {
		id = employee.getId();
		name = employee.getName();
		email = employee.getEmail();
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
	
	
}
