package com.tp.trinken.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.Cart;
import com.tp.trinken.entity.CartItem;
import com.tp.trinken.entity.Product;
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

	@Override
	public boolean existsByProductAndCart(Product product, Cart cart) {
		return cartItemRepo.existsByProductAndCart(product, cart);
	}

	@Override
	public Optional<CartItem> findOneByCartAndProduct(Cart cart, Product product) {
		return cartItemRepo.findOneByCartAndProduct(cart, product);
	}

	@Override
	public List<CartItem> findAllByCart(Cart cart) {
		return cartItemRepo.findAllByCart(cart);
	}

	@Override
	public Optional<CartItem> findOneById(Integer id) {

		return cartItemRepo.findById(id);
	}

	@Override
	public void deleteById(int id) {
		cartItemRepo.deleteById(id);
	}

}
