package com.tp.trinken.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProfileDto {
	private String userName;

	private String firstName;

	private String lastName;

	private String gender;

	private String email;

	private String phoneNumber;

	private String address;
	
	private MultipartFile imageFile;
}
