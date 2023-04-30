package com.tp.trinken.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tp.trinken.entity.Order;
import com.tp.trinken.entity.OrderStatus;
import com.tp.trinken.entity.User;
import com.tp.trinken.repository.OrderRepo;
import com.tp.trinken.service.OrderService;

public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepo orderRepo;

	@Override
	public <S extends Order> S save(S entity) {
		return orderRepo.save(entity);
	}

	@Override
	public List<Order> findByCustomerAndOrderStatus(User customer, OrderStatus orderStatus) {
		
		return orderRepo.findByCustomerAndOrderStatus(customer, orderStatus);
	}

}