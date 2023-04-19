package com.tp.trinken.service;

import java.util.List;
import java.util.Optional;

import com.tp.trinken.entity.Product;

public interface ProductService {

	List<Product> findAll();

	<S extends Product> S save(S entity);
	
	Boolean checkExitsProductName(String ProductName);

	Boolean existsByCategoryName(String name);

	Optional<Product> findById(Integer id);

}
