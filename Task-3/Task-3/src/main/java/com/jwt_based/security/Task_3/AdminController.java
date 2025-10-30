package com.jwt_based.security.Task_3;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/dashboard")
	public ResponseEntity<?> getAdminDashboard() {
		return ResponseEntity.ok("Welcome to the admin dashboard");
	}
}
