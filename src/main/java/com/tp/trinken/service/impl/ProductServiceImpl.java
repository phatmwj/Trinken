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
	public Boolean existsById(int id) {
		return productRepo.existsById(id);
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

	@Override
	public List<Product> findByActive(boolean active) {
		return productRepo.findByActive(active);
	}

	@Override
	public List<Product> getProductByCategoryAndActive(Integer active, Integer category_id) {
		return productRepo.getProductByCategoryAndActive(active, category_id);
	}

	@Override
	public List<Product> findAllProductByCategoryAndActiveOrderByPriceAsc(Integer active, Integer category_id) {
		return productRepo.findAllProductByCategoryAndActiveOrderByPriceAsc(active, category_id);
	}

	@Override
	public List<Product> findAllProductByCategoryAndActiveOrderByPriceDesc(Integer active, Integer category_id) {
		return productRepo.findAllProductByCategoryAndActiveOrderByPriceDesc(active, category_id);
	}

	@Override
	public List<Product> findAllProductByCategoryIdAndActiveOrderByIdAsc(Integer active, Integer category_id) {
		return productRepo.findAllProductByCategoryIdAndActiveOrderByIdAsc(active, category_id);
	}

	@Override
	public List<Product> findAllProductByCategoryIdAndActiveOrderByIdDesc(Integer active, Integer category_id) {
		return productRepo.findAllProductByCategoryIdAndActiveOrderByIdDesc(active, category_id);
	}

	@Override
	public List<Product> findAllProductByCategoryIdAndActiveOrderByProductNameAsc(Integer active, Integer category_id) {
		return productRepo.findAllProductByCategoryIdAndActiveOrderByProductNameAsc(active, category_id);
	}

	@Override
	public List<Product> findAllProductByCategoryIdAndActiveOrderByProductNameDesc(Integer active,
			Integer category_id) {
		return productRepo.findAllProductByCategoryIdAndActiveOrderByProductNameDesc(active, category_id);
	}

	@Override
	public List<Product> findAllProductByCategoryIdAndActiveOrderByProductSoldAsc(Integer active, Integer category_id) {
		return productRepo.findAllProductByCategoryIdAndActiveOrderByProductSoldAsc(active, category_id);
	}

	@Override
	public List<Product> findAllProductByCategoryIdAndActiveOrderByProductSoldDesc(Integer active,
			Integer category_id) {
		return productRepo.findAllProductByCategoryIdAndActiveOrderByProductSoldDesc(active, category_id);
	}

}
