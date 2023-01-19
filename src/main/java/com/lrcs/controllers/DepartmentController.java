package com.lrcs.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Page<DepartmentDTO>> findAll(
			@RequestParam(value = "page",defaultValue = "0")Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12")Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "DESC")String direction,
			@RequestParam(value = "orderBy",defaultValue = "name")String orderBy
			){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return ResponseEntity.ok().body(departmentService.findAllPaged(pageRequest));
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
