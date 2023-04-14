package com.tp.trinken.service;

import java.util.List;

import com.tp.trinken.entity.Product;

public interface ProductService {

	List<Product> findAll();

	<S extends Product> S save(S entity);

}
