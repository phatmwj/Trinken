package com.tp.trinken.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.model.User;
import com.tp.trinken.repository.UserRepo;
import com.tp.trinken.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepo userRepo;

	@Override
	public List<User> findAll() {
		
		return userRepo.findAll();
	}

	@Override
	public User login(String username, String password) {
		
		return null;
	}
	

}