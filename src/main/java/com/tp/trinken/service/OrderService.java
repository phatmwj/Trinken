package com.tp.trinken.service;

import java.util.List;
import java.util.Optional;

import com.tp.trinken.entity.Order;
import com.tp.trinken.entity.OrderStatus;
import com.tp.trinken.entity.User;

public interface OrderService {
	
	<S extends Order> S save(S entity);
	
	List<Order> findByCustomerAndOrderStatus(User customer, OrderStatus orderStatus);
	
	List<Order> findByCustomer(User custommer);

	List<Order> findByOrderStatus(OrderStatus orderStatus);

	List<Order> findAll();

	boolean existsById(Integer id);

	Optional<Order> findById(Integer id);
}
