package com.tp.trinken.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.CartItem;
import com.tp.trinken.repository.CartItemRepo;
import com.tp.trinken.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	CartItemRepo cartItemRepo;

	@Override
	public <S extends CartItem> S save(S entity) {
		return cartItemRepo.save(entity);
	}

}
