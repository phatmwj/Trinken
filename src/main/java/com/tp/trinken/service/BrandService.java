package com.tp.trinken.service;

import com.tp.trinken.entity.Brand;

public interface BrandService {

	Boolean checkExistsByBrandName(String name);

	<S extends Brand> S save(S entity);
	
	Brand findById(Integer id);

}
