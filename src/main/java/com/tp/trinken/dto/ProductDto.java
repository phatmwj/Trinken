package com.tp.trinken.dto;

import java.util.List;

import com.tp.trinken.entity.Asset;
import com.tp.trinken.entity.Brand;
import com.tp.trinken.entity.Category;

import lombok.Data;

@Data
public class ProductDto {
	private String productName;
	private String description;
	private double price;

	private int quantity;
	private Brand brand;

	private List<Category> catgories;
	private List<Asset> assets;

}
