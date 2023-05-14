package com.tp.trinken.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.Brand;
import com.tp.trinken.repository.BrandRepo;
import com.tp.trinken.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
	@Autowired
	BrandRepo brandRepo;

	@Override
	public Boolean checkExistsByBrandName(String name) {

		return brandRepo.existsByBrandName(name);
	}

	@Override
	public <S extends Brand> S save(S entity) {
		return brandRepo.save(entity);
	}

	@Override
	public Brand findById(Integer id) {

		return brandRepo.findById(id).get();
	}

	@Override
	public List<Brand> findAllByActive(boolean active) {
		return brandRepo.findAllByActive(active);
	}

}
