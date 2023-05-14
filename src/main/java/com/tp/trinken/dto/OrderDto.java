package com.tp.trinken.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tp.trinken.entity.OrderItem;
import com.tp.trinken.entity.OrderStatus;
import com.tp.trinken.entity.PaymentMethod;
import com.tp.trinken.entity.Product;
import com.tp.trinken.entity.ShippingAddress;
import com.tp.trinken.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class OrderDto {

	private Date orderDate;

	private double totalAmount;
	
	private Date cancelAt;
	
	private Date completeAt;
	
	private Date deliveryAt;
	
	private OrderStatus orderStatus;
	
	private User customer;
	
	private PaymentMethod paymentmethod;
	
	private ShippingAddress shippingAddress;
	
	private List<OrderItem> orderItems;
	

}
