package com.tp.trinken.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.OrderStatus;
import com.tp.trinken.repository.OrderStatusRepo;
import com.tp.trinken.service.OrderStatusService;

@Service
public class OrderStatusServiceImpl implements OrderStatusService {

	@Autowired
	OrderStatusRepo orderStatusRepo;

	@Override
	public Optional<OrderStatus> findOneById(Integer id) {

		return orderStatusRepo.findById(id);
	}

	@Override
	public Optional<OrderStatus> findById(Integer id) {
		return orderStatusRepo.findById(id);
	}

}
