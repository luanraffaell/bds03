package com.lrcs.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lrcs.entities.DTO.EmployeeDTO;
import com.lrcs.service.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
	
	EmployeeService employeeService;
	
	public EmployeeController(EmployeeService ur) {
		this.employeeService = ur;
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> findAll(){
		return ResponseEntity.ok().body(employeeService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> findById(@PathVariable Long id){
		EmployeeDTO employee = employeeService.findById(id);
		return ResponseEntity.ok().body(employee);
	}
	
	@PostMapping
	public ResponseEntity<EmployeeDTO> insert(@Valid @RequestBody EmployeeDTO employee){
		EmployeeDTO insert = employeeService.insert(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(insert);
	}
}
