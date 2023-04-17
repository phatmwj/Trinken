package com.tp.trinken.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CategoryDto {
	private String name;
	private MultipartFile image;

}
