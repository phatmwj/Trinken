package com.tp.trinken.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.OrderItem;
import com.tp.trinken.repository.OrderItemRepo;
import com.tp.trinken.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {
	@Autowired
	OrderItemRepo itemRepo;

	@Override
	public List<OrderItem> getOrderItemByOrderId(Integer orderId) {
		return itemRepo.getOrderItemByOrderId(orderId);
	}
}
