package com.lrcs.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lrcs.entities.DTO.UserDTO;
import com.lrcs.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	UserService userService;
	
	public UserController(UserService ur) {
		this.userService = ur;
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		return ResponseEntity.ok().body(userService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable Long id){
		UserDTO user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}
}
