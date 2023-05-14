package com.tp.trinken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.OrderStatus;

@Repository
public interface OrderStatusRepo extends JpaRepository<OrderStatus, Integer>{

}
