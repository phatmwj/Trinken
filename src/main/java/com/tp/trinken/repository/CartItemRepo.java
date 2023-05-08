package com.tp.trinken.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.Cart;
import com.tp.trinken.entity.CartItem;
import com.tp.trinken.entity.Product;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {
	boolean existsByProductAndCart(Product product, Cart cart);

	Optional<CartItem> findOneByCartAndProduct(Cart cart, Product product);

	List<CartItem> findAllByCart(Cart cart);

	void deleteById(int id);
}
