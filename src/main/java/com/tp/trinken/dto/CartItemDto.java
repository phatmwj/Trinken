package com.tp.trinken.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CartItemDto {
	private int quantity;
	private double price;

	private Date createdAt;

	private Date updatedAt;

	private int productId;
	private int cartId;
}
