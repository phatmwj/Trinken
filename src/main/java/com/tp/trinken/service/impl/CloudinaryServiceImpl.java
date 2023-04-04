package com.tp.trinken.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tp.trinken.service.CloudinaryService;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

	@Autowired
	Cloudinary cloudinary;

	@SuppressWarnings("rawtypes")
	@Override
	public String upload(MultipartFile file) {
		try {
			Map r = this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
			return (String)r.get("secure_url");
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void delete(String image)  {
		String[] a=image.split("/");
		String[] b=a[a.length-1].split("\\.");
		String public_id =b[0];
		try {
			Map r= cloudinary.uploader().destroy(public_id, ObjectUtils.asMap("resource_type","image"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
