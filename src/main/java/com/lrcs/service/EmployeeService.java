package com.lrcs.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.lrcs.entities.Department;
import com.lrcs.entities.Employee;
import com.lrcs.entities.DTO.EmployeeDTO;
import com.lrcs.repository.DepartmentRepository;
import com.lrcs.repository.EmployeeRepository;
import com.lrcs.service.exceptions.BusinessError;
import com.lrcs.service.exceptions.EntityNotFoundException;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	private DepartmentRepository departmentRepository;
	
	public EmployeeService(EmployeeRepository ur,DepartmentRepository dr) {
		this.employeeRepository = ur;
		this.departmentRepository = dr;
	}
	
	public List<EmployeeDTO> findAll() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map(x -> new EmployeeDTO(x)).collect(Collectors.toList());
	}
	
	public EmployeeDTO findById(Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() ->  new EntityNotFoundException("Employee not found"));
		 return new EmployeeDTO(employee);
	}
	@Transactional
	public EmployeeDTO insert(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		copyToEntity(employeeDTO, employee);
		return new EmployeeDTO(employeeRepository.save(employee));
	}
	
	private void copyToEntity(EmployeeDTO dto, Employee employee) {
		employee.setName(dto.getName());
		employee.setEmail(dto.getEmail());
		try {
		employee.setDepartment(findDepartment(dto));
		}catch(EntityNotFoundException e) {
			throw new BusinessError(e.getMessage());
		}
		
	}
	private Department findDepartment(EmployeeDTO dto) {
		
		Department department = departmentRepository.findById(dto.getDepartment())
				.orElseThrow(() -> new EntityNotFoundException("Department not found"));
		return department;
	}
}
