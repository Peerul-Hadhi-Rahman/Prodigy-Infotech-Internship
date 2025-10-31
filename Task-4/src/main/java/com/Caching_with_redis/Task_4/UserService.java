package com.Caching_with_redis.Task_4;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Cacheable(value = "users")
	public List<User> getAllUsers() {
		simulateSlowService();
		return userRepository.findAll();
	}
	
	@Cacheable(value = "user", key = "#id")
	public Optional<User> getUserById(Long id) {
		simulateSlowService();
		return userRepository.findById(id);
	}
	
	@CacheEvict(value = "users", allEntries = true)
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	@CachePut(value = "user", key = "#user.id")
	@CacheEvict(value = "users", allEntries = true)
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	@CacheEvict(value = {"users", "user"}, allEntries = true)
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	private void simulateSlowService() {
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
