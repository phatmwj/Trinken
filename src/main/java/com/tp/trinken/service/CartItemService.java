package com.tp.trinken.service;

import java.util.List;
import java.util.Optional;

import com.tp.trinken.entity.Cart;
import com.tp.trinken.entity.CartItem;
import com.tp.trinken.entity.Product;

public interface CartItemService {

	<S extends CartItem> S save(S entity);

	boolean existsByProductAndCart(Product product, Cart cart);

	Optional<CartItem> findOneByCartAndProduct(Cart cart, Product product);

	List<CartItem> findAllByCart(Cart cart);
	
	Optional<CartItem> findOneById(Integer id);

}
