package com.tp.trinken.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.PaymentMethod;
import com.tp.trinken.repository.PaymentMethodRepo;
import com.tp.trinken.service.PaymentMethodService;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService{
	
	@Autowired
	PaymentMethodRepo paymentMethodRepo;

	@Override
	public List<PaymentMethod> findAll() {
		
		return paymentMethodRepo.findAll();
	}

	@Override
	public Optional<PaymentMethod> findOneById(Integer id) {
		
		return paymentMethodRepo.findById(id);
	}

}
