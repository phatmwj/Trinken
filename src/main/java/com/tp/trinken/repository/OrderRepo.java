package com.tp.trinken.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.Order;
import com.tp.trinken.entity.OrderStatus;
import com.tp.trinken.entity.User;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
	
	List<Order> findByCustomerAndOrderStatus(User customer, OrderStatus orderStatus);

}
