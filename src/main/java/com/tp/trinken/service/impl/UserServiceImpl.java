package com.tp.trinken.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.Cart;
import com.tp.trinken.entity.User;
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
	public Optional<User> login(String username, String password) {
		
		return userRepo.findOneByUserNameAndPassword(username, password);
	}

	@Override
	public Boolean checkUsername(String username) {
		
		return userRepo.existsByUserName(username);
	}

	@Override
	public Boolean checkEmail(String email) {
		
		return userRepo.existsByEmail(email);
	}

	@Override
	public Optional<User> findByUserName(String username) {
		
		return userRepo.findOneByUserName(username);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		
		return userRepo.findOneByEmail(email);
	}

	@Override
	public <S extends User> S save(S entity) {
		
		return userRepo.save(entity);
	}

	@Override
	public Optional<User> findById(Integer id) {
		
		return userRepo.findById(id);
	}

	@Override
	public Optional<User> findOneByCart(Cart cart) {
		
		return userRepo.findOneByCart(cart);
	}

}