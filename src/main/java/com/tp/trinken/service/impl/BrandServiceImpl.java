package com.tp.trinken.service.impl;

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
		// TODO Auto-generated method stub
		return brandRepo.existsByBrandName(name);
	}

	@Override
	public <S extends Brand> S save(S entity) {
		return brandRepo.save(entity);
	}

}
