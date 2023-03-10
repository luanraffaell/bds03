package com.lrcs.entities.DTO;

import javax.validation.constraints.NotBlank;

import com.lrcs.entities.Department;

public class DepartmentDTO {
	
	private Long id;
	@NotBlank
	private String name;
	
	public DepartmentDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public DepartmentDTO(Department department) {
		id = department.getId();
		name = department.getName();
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
	
}
