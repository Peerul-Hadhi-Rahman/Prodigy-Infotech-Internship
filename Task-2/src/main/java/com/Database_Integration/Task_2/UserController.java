package com.Database_Integration.Task_2;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserService userService;
	private final Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w+$");
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		if (!emailPattern.matcher(user.getEmail()).matches()) {
			return ResponseEntity.badRequest().body("Invalid email format");
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userService.createUser(user));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUser(@PathVariable UUID id) {
	    Optional<User> user = userService.getUser(id);
	    if (user.isPresent()) {
	        return ResponseEntity.ok(user.get());
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("User not found");
	    }
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		return ResponseEntity.ok(userService.getAllUser());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable UUID id, @Valid @RequestBody User user) {
	    if (!emailPattern.matcher(user.getEmail()).matches()) {
	        return ResponseEntity.badRequest().body("Invalid email format");
	    }
	    
	    Optional<User> updatedUser = userService.updateUser(id, user);
	    if (updatedUser.isPresent()) {
	        return ResponseEntity.ok(updatedUser.get());
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .body("User not found");
	    }
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
		boolean deleted = userService.deleteUser(id);
		if(!deleted) return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("User not found");
		return ResponseEntity.noContent().build();
	}
}
