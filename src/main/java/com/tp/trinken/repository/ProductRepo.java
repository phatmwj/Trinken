package com.tp.trinken.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	Boolean existsByProductName(String productName);

	List<Product> findAllByActiveIsTrue();

	List<Product> findAllByActiveIsFalse();
}
