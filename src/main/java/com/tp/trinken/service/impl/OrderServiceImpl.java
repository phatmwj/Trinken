package com.tp.trinken.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.Order;
import com.tp.trinken.entity.OrderStatus;
import com.tp.trinken.entity.User;
import com.tp.trinken.repository.OrderRepo;
import com.tp.trinken.service.OrderService;

@Service
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

	@Override
	public List<Order> findByCustomer(User custommer) {
		// TODO Auto-generated method stub
		return orderRepo.findByCustomer(custommer);
	}

	@Override
	public List<Order> findByOrderStatus(OrderStatus orderStatus) {
		return orderRepo.findByOrderStatus(orderStatus);
	}

	@Override
	public List<Order> findAll() {
		return orderRepo.findAll();
	}

	@Override
	public boolean existsById(Integer id) {
		return orderRepo.existsById(id);
	}

	@Override
	public Optional<Order> findById(Integer id) {
		return orderRepo.findById(id);
	}

}
