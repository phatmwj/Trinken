package com.tp.trinken.service.impl;

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
		// TODO Auto-generated method stub
		return categoryRepo.findByActive(active);
	}

	@Override
	public Boolean existsByCategoryName(String name) {
		// TODO Auto-generated method stub
		return categoryRepo.existsByCategoryName(name);
	}

}
