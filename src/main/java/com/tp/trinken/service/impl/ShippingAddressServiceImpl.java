package com.tp.trinken.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.ShippingAddress;
import com.tp.trinken.repository.ShippingAddressRepo;
import com.tp.trinken.service.ShippingAddressService;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
	
	@Autowired
	ShippingAddressRepo shippingAddressRepo;

	@Override
	public <S extends ShippingAddress> S save(S entity) {
		return shippingAddressRepo.save(entity);
	}

	@Override
	public List<ShippingAddress> findByUserId(Integer id) {
		
		return shippingAddressRepo.findByUserId(id);
	}

	@Override
	public void deleteById(Integer id) {
		shippingAddressRepo.deleteById(id);;
		
	}
	
}
