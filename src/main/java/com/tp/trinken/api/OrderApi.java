package com.tp.trinken.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tp.trinken.dto.OrderDto;
import com.tp.trinken.entity.Order;
import com.tp.trinken.entity.OrderItem;
import com.tp.trinken.service.OrderItemService;
import com.tp.trinken.service.OrderService;
import com.tp.trinken.utils.Result;

@RestController
@RequestMapping(value = "/order")
public class OrderApi {
    
//	@Autowired
//	OrderService orderService;
//	
//	@Autowired 
//	OrderItemService orderItemService;
	
	Result result;
	
	
//	// user order
//	@PostMapping(value = "/add")
//	public ResponseEntity<?> addOrder(@Valid @RequestBody OrderDto orderDto){
//		Order order = new Order();
//		List<OrderItem>orderItems = new ArrayList<>();
//		return null;
//	}
	
}
