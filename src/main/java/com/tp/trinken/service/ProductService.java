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

	List<Product> findByActive(boolean active);

	List<Product> getProductByCategoryAndActive(Integer active, Integer category_id);

	List<Product> findAllProductByCategoryAndActiveOrderByPriceDesc(Integer active, Integer category_id);

	List<Product> findAllProductByCategoryAndActiveOrderByPriceAsc(Integer active, Integer category_id);

	List<Product> findAllProductByCategoryIdAndActiveOrderByIdDesc(Integer active, Integer category_id);

	List<Product> findAllProductByCategoryIdAndActiveOrderByIdAsc(Integer active, Integer category_id);

	List<Product> findAllProductByCategoryIdAndActiveOrderByProductNameDesc(Integer active, Integer category_id);

	List<Product> findAllProductByCategoryIdAndActiveOrderByProductNameAsc(Integer active, Integer category_id);

	List<Product> findAllProductByCategoryIdAndActiveOrderByProductSoldDesc(Integer active, Integer category_id);

	List<Product> findAllProductByCategoryIdAndActiveOrderByProductSoldAsc(Integer active, Integer category_id);

	Boolean existsById(int id);

}
