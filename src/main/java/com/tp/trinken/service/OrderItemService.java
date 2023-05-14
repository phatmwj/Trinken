package com.tp.trinken.service;

import java.util.List;

import com.tp.trinken.entity.OrderItem;

public interface OrderItemService {

	List<OrderItem> getOrderItemByOrderId(Integer orderId);

}
