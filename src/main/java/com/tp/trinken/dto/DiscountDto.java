package com.tp.trinken.dto;

import java.util.Date;

import com.tp.trinken.entity.DiscountType;

import lombok.Data;

@Data
public class DiscountDto {
	private String discountCode;
	private DiscountType discountType;
	private double discountValue;
	private Date startDate;
	private Date endDate;
}
