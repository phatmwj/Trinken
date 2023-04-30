package com.tp.trinken.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.Discount;
import com.tp.trinken.repository.DiscountRepo;
import com.tp.trinken.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {
	@Autowired
	DiscountRepo discountRepo;

	@Override
	public <S extends Discount> S save(S entity) {
		return discountRepo.save(entity);
	}

}
