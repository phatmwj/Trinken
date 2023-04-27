package com.tp.trinken.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.tp.trinken.entity.Cart;
import com.tp.trinken.entity.Role;

import lombok.Data;

@Data
public class UserDto {
	
	private Integer id;
	
	private String firstName;

	private String lastName;

	private String gender;

	private String phoneNumber;

	private String address;
	
	private String image;

	private boolean active;
	
	private Date createdAt;
	
	private Date updatedAt;

	private Date lastLogin;

	private Role role;
	
	private Cart cart;
	
	private MultipartFile imageFile;
}
