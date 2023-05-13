package com.tp.trinken.service;

import java.util.List;

import com.tp.trinken.entity.Brand;

public interface BrandService {

	Boolean checkExistsByBrandName(String name);

	<S extends Brand> S save(S entity);
	
	Brand findById(Integer id);

	List<Brand> findAllByActive(boolean active);

}
