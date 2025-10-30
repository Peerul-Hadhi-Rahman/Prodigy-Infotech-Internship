package com.jwt_based.security.Task_3;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@GetMapping
	public ResponseEntity<?> getUserProfile() {
		return ResponseEntity.ok("Welcome, authenticated user!");
	}
}
