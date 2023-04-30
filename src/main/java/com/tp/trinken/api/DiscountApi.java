package com.tp.trinken.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tp.trinken.dto.DiscountDto;
import com.tp.trinken.entity.Discount;
import com.tp.trinken.service.DiscountService;
import com.tp.trinken.utils.Result;

@RestController
@RequestMapping(value = "/discount")
public class DiscountApi {
	@Autowired
	DiscountService discountService;

	Result result = new Result();;

	@PostMapping(value = "add")
	public ResponseEntity<?> addNewDiscount(DiscountDto discountDto) {
		Discount discount = new Discount();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		BeanUtils.copyProperties(discountDto, discount);
		try {
			/*
			 * discount.setStartDate(dateFormat.parse(discount.getStartDate().toString()));
			 * discount.setEndDate(dateFormat.parse(discount.getEndDate().toString()));
			 */

			Discount x = discountService.save(discount);

			return new ResponseEntity<>(x, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(result.result(true, "Failed"), HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
