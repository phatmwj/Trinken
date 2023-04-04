package com.tp.trinken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@SpringBootApplication
public class TrinkenApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrinkenApplication.class, args);	
	}
	@Bean
	public Cloudinary cloudinary() {
		Cloudinary c=new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dqnogv8iz",
				"api_key", "796285712551151",
				"api_secret", "PdUCEelGAWB4R9cahOqFlYQ0JEQ",
				"secure",true
				));
		return c;
	}

}
