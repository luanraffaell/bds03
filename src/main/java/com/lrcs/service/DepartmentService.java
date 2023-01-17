package com.lrcs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lrcs.entities.Department;
import com.lrcs.entities.DTO.DepartmentDTO;
import com.lrcs.repository.DepartmentRepository;
import com.lrcs.service.exceptions.EntityNotFoundException;

@Service
public class DepartmentService {
	
	private DepartmentRepository departmentRepository;
	
	public DepartmentService(DepartmentRepository ur) {
		this.departmentRepository = ur;
	}
	
	public List<DepartmentDTO> findAll() {
		List<Department> departments = departmentRepository.findAll();
		return departments.stream().map(x -> new DepartmentDTO(x)).collect(Collectors.toList());
	}
	
	public DepartmentDTO findById(Long id) {
		Department department = departmentRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException("Department not found"));
		 return new DepartmentDTO(department);
	}
	
	public DepartmentDTO insert(DepartmentDTO departmentDTO) {
		Department department = new Department();
		copyToEntity(departmentDTO, department);
		return new DepartmentDTO(departmentRepository.save(department));
	}
	
	public void copyToEntity(DepartmentDTO dto, Department department) {
		department.setName(dto.getName());
	}
}
