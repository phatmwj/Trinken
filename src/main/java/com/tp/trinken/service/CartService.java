package com.tp.trinken.service;

import java.util.List;
import java.util.Optional;

import com.tp.trinken.entity.Cart;
import com.tp.trinken.entity.User;

public interface CartService {

	List<Cart> findAll();

	<S extends Cart> S save(S entity);

	List<Cart> getByCustomer(User customer);

	Optional<Cart> findOneByCustomer(User customer);

	Optional<Cart> findOneById(int id);

}
