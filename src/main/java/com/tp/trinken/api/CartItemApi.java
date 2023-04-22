package com.tp.trinken.api;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tp.trinken.dto.CartItemDto;
import com.tp.trinken.entity.Cart;
import com.tp.trinken.entity.CartItem;
import com.tp.trinken.entity.Product;
import com.tp.trinken.service.CartItemService;
import com.tp.trinken.service.CartService;
import com.tp.trinken.service.ProductService;
import com.tp.trinken.service.UserService;
import com.tp.trinken.utils.Result;

@RestController
@RequestMapping("/cartitem")
public class CartItemApi {
	@Autowired
	CartItemService cartItemService;
	@Autowired
	CartService cartService;
	@Autowired
	UserService service;
	@Autowired
	ProductService productService;

	Result rs;

	@PostMapping(value = "/add")
	public ResponseEntity<?> addCartItem(@Valid @RequestBody CartItemDto cartItemDto) {
		Product product = productService.findById(cartItemDto.getProductId()).get();
		Cart cart = cartService.findOneById(cartItemDto.getCartId()).get();
		if (cart != null) {
			CartItem cartItem = new CartItem();
			BeanUtils.copyProperties(cartItemDto, cartItem);
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItemService.save(cartItem);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}
	}

}
