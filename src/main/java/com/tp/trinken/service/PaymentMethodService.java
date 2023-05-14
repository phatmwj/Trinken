package com.tp.trinken.service;

import java.util.List;
import java.util.Optional;

import com.tp.trinken.entity.PaymentMethod;

public interface PaymentMethodService {
   
	List<PaymentMethod> findAll();
	
	Optional<PaymentMethod> findOneById(Integer id);
}
