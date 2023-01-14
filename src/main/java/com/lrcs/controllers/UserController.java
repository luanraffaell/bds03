package com.lrcs.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok("blabla");
	}
}
