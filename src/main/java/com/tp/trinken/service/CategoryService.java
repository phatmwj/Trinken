package com.tp.trinken.service;

import java.util.List;
import java.util.Optional;

import com.tp.trinken.entity.Category;

public interface CategoryService {

	Optional<Category> findById(Integer id);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	List<Category> findByActive(boolean active);


	Boolean checkCategoryName(String name);
	
	List<Category> findAllByIds(List<Integer> ids);

	Boolean existsByCategoryName(String name);

	List<Category> findByCategoryName(String name);


}
