package com.tp.trinken.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.Cart;
import com.tp.trinken.entity.User;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
	List<Cart> getByCustomer(User customer);

	Optional<Cart> findOneByCustomer(User customer);

}
