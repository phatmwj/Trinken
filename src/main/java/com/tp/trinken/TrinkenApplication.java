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
		Cloudinary c = new Cloudinary(ObjectUtils.asMap("cloud_name", "dc6weg8vp", "api_key", "915129338674733",
				"api_secret", "K4EOcGg37-64fjSWaeR5-y4Y9P0", "secure", true));
		return c;
	}

}
