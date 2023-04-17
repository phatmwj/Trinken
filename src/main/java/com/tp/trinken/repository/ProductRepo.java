package com.tp.trinken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}