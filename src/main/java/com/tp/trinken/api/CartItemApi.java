package com.tp.trinken.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tp.trinken.dto.CartItemDto;
import com.tp.trinken.entity.Cart;
import com.tp.trinken.entity.CartItem;
import com.tp.trinken.entity.DiscountType;
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

	Result rs = new Result();

	@GetMapping(value = "/get-all/{id}")
	public ResponseEntity<?> getAllByCart(@Valid @PathVariable Integer id) {
		Cart cart = cartService.findOneById(id).get();
		List<CartItem> list = new ArrayList<>();
		list = cartItemService.findAllByCart(cart);
		if (list != null) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> addCartItem(@Valid @RequestBody CartItemDto cartItemDto) {
		Product product = productService.findById(cartItemDto.getProductId()).get();
		Cart cart = cartService.findOneById(cartItemDto.getCartId()).get();
		if (cart != null && product.isActive()) {
			Optional<CartItem> cartItemOptional = cartItemService.findOneByCartAndProduct(cart, product);
			CartItem cartItem = new CartItem();
			cartItem.setCart(cart);
			cartItem.setProduct(product);
			// check xem trong cartItem của người này đã có sản phẩm này chưa
			if (!cartItemOptional.isEmpty()) {
				cartItem = cartItemOptional.get();
				cartItem.setQuantity(cartItem.getQuantity() + cartItemDto.getQuantity());
				if (product.getDiscount() != null && product.getDiscount().getStatus() != 0) {
					Date date = new Date();
					if (date.before(product.getDiscount().getEndDate())
							&& date.after(product.getDiscount().getStartDate())) {
						if (product.getDiscount().getDiscountType() == DiscountType.percent) {
							cartItem.setPrice(product.getPrice() * (100 - product.getDiscount().getDiscountValue())
									* cartItem.getQuantity() / 100);
						} else {
							cartItem.setPrice((product.getPrice() - product.getDiscount().getDiscountValue())
									* cartItem.getQuantity());
						}
					}

				} else {
					cartItem.setPrice(product.getPrice() * cartItem.getQuantity());
				}
			} else {
				if (product.getDiscount() != null && product.getDiscount().getStatus() != 0) {
					Date date = new Date();
					if (date.before(product.getDiscount().getEndDate())
							&& date.after(product.getDiscount().getStartDate())) {
						if (product.getDiscount().getDiscountType() == DiscountType.percent) {
							cartItemDto.setPrice(product.getPrice() * (100 - product.getDiscount().getDiscountValue())
									* cartItemDto.getQuantity() / 100);
						} else {
							cartItemDto.setPrice((product.getPrice() - product.getDiscount().getDiscountValue())
									* cartItemDto.getQuantity());
						}
					}

				} else {
					cartItemDto.setPrice(product.getPrice() * cartItemDto.getQuantity());
				}
				BeanUtils.copyProperties(cartItemDto, cartItem);
			}

			cartItemService.save(cartItem);
			return new ResponseEntity<>(cartItem, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}
	}

	@PutMapping(value = "/update-quantity/{id}")
	public void UpdateQuantity(@PathVariable("id") Integer id, @RequestParam Integer count) {
		CartItem cartItem = cartItemService.findOneById(id).get();
		cartItem.setQuantity(count);
		if (cartItem.getProduct().getDiscount() != null && cartItem.getProduct().getDiscount().getStatus() != 0) {
			Date date = new Date();
			if (date.before(cartItem.getProduct().getDiscount().getEndDate())
					&& date.after(cartItem.getProduct().getDiscount().getStartDate())) {
				if (cartItem.getProduct().getDiscount().getDiscountType() == DiscountType.percent) {
					cartItem.setPrice(cartItem.getProduct().getPrice()
							* (100 - cartItem.getProduct().getDiscount().getDiscountValue()) * cartItem.getQuantity()
							/ 100);
				} else {
					cartItem.setPrice(
							(cartItem.getProduct().getPrice() - cartItem.getProduct().getDiscount().getDiscountValue())
									* cartItem.getQuantity());
				}
			}
		} else {
			cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
		}

		cartItemService.save(cartItem);
	}

	@DeleteMapping(value = "/delete/{cartItemId}")
	public ResponseEntity<?> deleteCartItem(@PathVariable("cartItemId") Integer cartItemId) {
		Optional<CartItem> cartItem = cartItemService.findOneById(cartItemId);
		if (cartItem != null) {
			cartItemService.deleteById(cartItemId);
			return new ResponseEntity<>(rs.result(false, "Deleted successfully!"), HttpStatus.OK);
		}
		return new ResponseEntity<>(rs.result(false, "There is no!"), HttpStatus.NOT_IMPLEMENTED);
	}

}
