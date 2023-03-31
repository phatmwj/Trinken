package com.tp.trinken.service;

import java.util.List;
import java.util.Optional;

import com.tp.trinken.model.User;

public interface UserService {
	List<User> findAll();
	
	User login(String username, String password);
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);
}
