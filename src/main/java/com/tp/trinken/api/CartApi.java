package com.tp.trinken.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tp.trinken.entity.Cart;
import com.tp.trinken.entity.User;
import com.tp.trinken.service.CartService;
import com.tp.trinken.service.UserService;
import com.tp.trinken.utils.Result;

@RestController
@RequestMapping("/cart")
public class CartApi {
	@Autowired
	CartService cartService;
	@Autowired
	UserService userService;
	Result rs;

	@PostMapping(value = "/get-by-customer-id/{id}")
	public ResponseEntity<?> getCartByCustomerId(@Valid @PathVariable Integer id) {
		Optional<User> customer = userService.findById(id);

		Optional<Cart> cart = cartService.findOneByCustomer(customer.get());
		if (cart.get() != null) {
			return new ResponseEntity<>(cart.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(rs.result(true, "Failed"), HttpStatus.NO_CONTENT);
		}

	}
}
