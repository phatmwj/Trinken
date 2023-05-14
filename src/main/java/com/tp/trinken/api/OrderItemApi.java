package com.tp.trinken.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tp.trinken.entity.OrderItem;
import com.tp.trinken.service.OrderItemService;
import com.tp.trinken.service.OrderService;
import com.tp.trinken.utils.Result;

@RestController
@RequestMapping(value = "/order-item")
public class OrderItemApi {
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	OrderService orderService;
	Result rs;

	@GetMapping(value = "/get-all-by-orderId/orderId={orderId}")
	public ResponseEntity<?> getOrderItemByOrderId(@PathVariable("orderId") Integer orderId) {
		if (orderService.existsById(orderId)) {
			List<OrderItem> orderItems = new ArrayList<>();
			orderItems = orderItemService.getOrderItemByOrderId(orderId);
			if (orderItems != null)
				return new ResponseEntity<>(orderItems, HttpStatus.OK);

		}
		return new ResponseEntity<>(rs.result(true, "There is no orderItem"), HttpStatus.NOT_FOUND);

	}
}
