package com.Database_Integration.Task_2;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public User createUser(User user) {
		return userRepository.save(user); 
	}
	public Optional<User> getUser(UUID id) {
		return userRepository.findById(id);
	}
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	public Optional<User> updateUser(UUID id, User updatedUser) {
		return userRepository.findById(id).map(existing -> {
			existing.setName(updatedUser.getName());
			existing.setEmail(updatedUser.getEmail());
			existing.setAge(updatedUser.getAge());
			return userRepository.save(existing);
				});
	}
	public boolean deleteUser(UUID id) {
		if (!userRepository.existsById(id)) return false;
		userRepository.deleteById(id);
		return true;
	}
}
