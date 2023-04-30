package com.tp.trinken.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductDto {
	private String productName;
	private String description;
	private double price;
	private MultipartFile imageFile;

	private int quantity;
	private int brandId;

	private List<Integer> categoryIds;
	private List<MultipartFile> imageFiles;

}
