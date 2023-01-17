package com.lrcs.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lrcs.entities.DTO.DepartmentDTO;
import com.lrcs.service.DepartmentService;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {
	
	DepartmentService departmentService;
	
	public DepartmentController(DepartmentService ur) {
		this.departmentService = ur;
	}
	
	@GetMapping
	public ResponseEntity<List<DepartmentDTO>> findAll(){
		return ResponseEntity.ok().body(departmentService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<DepartmentDTO> insert(@RequestBody DepartmentDTO department){
		return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.insert(department));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DepartmentDTO> findById(@PathVariable Long id){
		DepartmentDTO department = departmentService.findById(id);
		return ResponseEntity.ok().body(department);
	}
}