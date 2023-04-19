package com.tp.trinken.service.impl;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Boolean checkExitsProductName(String ProductName) {
		
		return productRepo.existsByProductName(ProductName);
  }
	public Boolean existsByCategoryName(String name) {
		// TODO Auto-generated method stub
		return productRepo.existsByProductName(name);
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return productRepo.findById(id);

	}

}
