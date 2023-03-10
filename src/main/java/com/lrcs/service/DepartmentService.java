package com.lrcs.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<DepartmentDTO> findAllPaged(Pageable page) {
		Page<Department> departments = departmentRepository.findAll(page);
		return departments.map(x -> new DepartmentDTO(x));
	}
	
	public DepartmentDTO findById(Long id) {
		Department department = departmentRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException("Department not found"));
		 return new DepartmentDTO(department);
	}
	
	@Transactional
	public DepartmentDTO insert(DepartmentDTO departmentDTO) {
		Department department = new Department();
		copyToEntity(departmentDTO, department);
		return new DepartmentDTO(departmentRepository.save(department));
	}
	
	public void copyToEntity(DepartmentDTO dto, Department department) {
		department.setName(dto.getName());
	}
}
