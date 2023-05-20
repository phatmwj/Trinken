package com.tp.trinken.service;

import java.util.Optional;

import com.tp.trinken.entity.OrderStatus;

public interface OrderStatusService {
	
	Optional<OrderStatus> findOneById(Integer id);

	Optional<OrderStatus> findById(Integer id);
}
