package com.tp.trinken.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {
	@Query(value = "SELECT * FROM order_items where order_id = :orderId", nativeQuery = true)
	List<OrderItem> getOrderItemByOrderId(Integer orderId);

}
