package com.tp.trinken.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
	//Upload a MultipartFile to Cloudinary
	String upload( MultipartFile file);
	
	//delete file
	void delete(String image);
}
