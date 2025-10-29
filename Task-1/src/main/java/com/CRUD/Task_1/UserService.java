package com.CRUD.Task_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final Map<UUID, User> userMap = new HashMap<>();
	
	public User createUser(User user) {
		user.setId(UUID.randomUUID());
		userMap.put(user.getId(), user);
		return user;
	}
	
	public User getUser(UUID id) {
		return userMap.get(id);
	}
	
	public List<User> getAllUsers() {
		return new ArrayList<>(userMap.values());
		}
	
	public User updateUser(UUID id, User updateUser) {
		if (!userMap.containsKey(id)) return null;
		updateUser.setId(id);
		userMap.put(id, updateUser);
		return updateUser;
	}
	
	public boolean deleteUser(UUID id) {
		return userMap.remove(id) != null;
	}
	
}
