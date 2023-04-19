package com.tp.trinken.service;

import java.util.List;
import java.util.Optional;

import com.tp.trinken.entity.User;

public interface UserService {
	
	List<User> findAll();
	
	Optional<User> login(String username, String password);
	
	Optional<User> findByUserName(String username);
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findById(Integer id);
	
	Boolean checkUsername(String username);
	
	Boolean checkEmail(String email);
	
	<S extends User> S save(S entity);
}
