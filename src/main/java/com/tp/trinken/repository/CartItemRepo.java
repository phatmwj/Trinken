package com.tp.trinken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.CartItem;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Integer> {

}
