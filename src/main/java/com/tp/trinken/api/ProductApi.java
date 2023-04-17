package com.tp.trinken.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tp.trinken.entity.Product;
import com.tp.trinken.service.CloudinaryService;
import com.tp.trinken.service.ProductService;
import com.tp.trinken.service.UserService;
import com.tp.trinken.utils.Result;

@RestController
@RequestMapping("/product")
public class ProductApi {
	@Autowired
	ProductService productService;

	@Autowired
	UserService userService;

	@Autowired
	CloudinaryService cloudinaryService;

	Result rs = new Result();

	@GetMapping(value = "/get-all")
	public ResponseEntity<?> getAllProduct() {
		List<Product> products = productService.findAll();

		if (products.size() > 0) {
			return new ResponseEntity<>(products, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(value = "/create")
	
}
