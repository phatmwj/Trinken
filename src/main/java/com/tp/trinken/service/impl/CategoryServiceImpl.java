package com.tp.trinken.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.Category;
import com.tp.trinken.repository.CategoryRepo;
import com.tp.trinken.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return categoryRepo.findById(id);
	}

	@Override
	public <S extends Category> S save(S entity) {
		return categoryRepo.save(entity);
	}

	@Override
	public List<Category> findByActive(boolean active) {

		return categoryRepo.findByActive(active);
	}

	@Override
	public Boolean checkCategoryName(String name) {
		return categoryRepo.existsByCategoryName(name);
	}

	public Boolean existsByCategoryName(String name) {
		return categoryRepo.existsByCategoryName(name);
	}

	@Override
	public List<Category> findAllByIds(List<Integer> ids) {
		List<Category> categories = new ArrayList<>();
		for (Integer id : ids) {
			categories.add(categoryRepo.findById(id).get());
		}
		return categories;
	}

	@Override
	public List<Category> findByCategoryName(String name) {
		return categoryRepo.findByCategoryName(name);
	}
}
