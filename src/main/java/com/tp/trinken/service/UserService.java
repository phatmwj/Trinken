package com.tp.trinken.service;

import java.util.List;

import com.tp.trinken.model.User;

public interface UserService {
	List<User> findAll();
	
	User login(String username, String password);
}
