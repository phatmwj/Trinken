package com.tp.trinken.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.Cart;
import com.tp.trinken.entity.User;
import com.tp.trinken.repository.CartRepo;
import com.tp.trinken.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartRepo cartRepo;

	@Override
	public List<Cart> getByCustomer(User customer) {
		return cartRepo.getByCustomer(customer);
	}

	@Override
	public <S extends Cart> S save(S entity) {
		return cartRepo.save(entity);
	}

	@Override
	public List<Cart> findAll() {
		return cartRepo.findAll();
	}

	@Override
	public Optional<Cart> findOneByCustomer(User customer) {
		return cartRepo.findOneByCustomer(customer);
	}

}
