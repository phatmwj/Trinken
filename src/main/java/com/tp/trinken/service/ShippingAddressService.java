package com.tp.trinken.service;


import java.util.List;

import com.tp.trinken.entity.ShippingAddress;

public interface ShippingAddressService {

	<S extends ShippingAddress> S save(S entity);
	
	List<ShippingAddress> findByUserId(Integer id);
	
	void deleteById(Integer id);
}
