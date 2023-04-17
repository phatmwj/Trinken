package com.tp.trinken.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.Product;
import com.tp.trinken.repository.ProductRepo;
import com.tp.trinken.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductRepo productRepo;

	@Override
	public <S extends Product> S save(S entity) {
		return productRepo.save(entity);
	}

	@Override
	public List<Product> findAll() {
		return productRepo.findAll();
	}

}
