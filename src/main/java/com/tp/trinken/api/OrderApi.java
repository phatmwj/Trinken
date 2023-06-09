package com.tp.trinken.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tp.trinken.dto.OrderDto;
import com.tp.trinken.entity.Cart;
import com.tp.trinken.entity.CartItem;
import com.tp.trinken.entity.Order;
import com.tp.trinken.entity.OrderItem;
import com.tp.trinken.entity.OrderStatus;
import com.tp.trinken.entity.User;
import com.tp.trinken.service.CartItemService;
import com.tp.trinken.service.CartService;
import com.tp.trinken.service.OrderItemService;
import com.tp.trinken.service.OrderService;
import com.tp.trinken.service.OrderStatusService;
import com.tp.trinken.service.PaymentMethodService;
import com.tp.trinken.service.ShippingAddressService;
import com.tp.trinken.service.UserService;
import com.tp.trinken.utils.Result;

@RestController
@RequestMapping(value = "/order")
public class OrderApi {

	@Autowired
	OrderService orderService;

	@Autowired
	OrderItemService orderItemService;

	@Autowired
	CartService cartService;

	@Autowired
	ShippingAddressService shippingAddressService;

	@Autowired
	PaymentMethodService paymentMethodService;

	@Autowired
	CartItemService cartItemService;

	@Autowired
	UserService userService;

	@Autowired
	OrderStatusService orderStatusService;

	Result result = new Result();

	// user order from cart
//	@PostMapping(value = "/add/{cartid}")
//	public ResponseEntity<?> addOrder(@PathVariable("cartid") Integer cartId){
//		OrderDto orderDto = new OrderDto();
//		List<OrderItem> orderItems = new ArrayList<>();
//		orderDto.setOrderItems(orderItems);
//		Cart cart = cartService.findOneById(cartId).get();
//		Order order = new Order();
//		List<CartItem> cartItems = cartItemService.findAllByCart(cart);
//		for(CartItem cartItem : cartItems) {
//			OrderItem orderItem = new OrderItem();
//			orderItem.setProduct(cartItem.getProduct());
//			orderItem.setQuantity(cartItem.getQuantity());
//			orderItem.setPrice(cartItem.getPrice());
//			orderDto.getOrderItems().add(orderItem);
//		}
//		orderDto.setOrderDate(Calendar.getInstance().getTime());
//		orderDto.setTotalAmount(1000);
//		BeanUtils.copyProperties(orderDto, order);
//		List<OrderItem> orderItems1 = orderDto.getOrderItems();
//		for(OrderItem orderItem: orderItems1) {
//			orderItem.setOrder(order);
//		}
//		try {
//			orderService.save(order);
//
//			return new ResponseEntity<>(result.result(false, "Đặt hàng thành công"), HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		
//	}
	// user order from cart
	@PostMapping(value = "/create/{cartid}")
	public ResponseEntity<?> createOrder(@PathVariable("cartid") Integer cartId, @RequestParam Integer shippingId,
			@RequestParam Integer paymentId) {
		OrderDto orderDto = new OrderDto();
		orderDto.setOrderStatus(orderStatusService.findOneById(1).get());
		orderDto.setShippingAddress(shippingAddressService.findById(shippingId).get());
		orderDto.setPaymentmethod(paymentMethodService.findOneById(paymentId).get());
		List<OrderItem> orderItems = new ArrayList<>();
		orderDto.setOrderItems(orderItems);
		Cart cart = cartService.findOneById(cartId).get();
		orderDto.setCustomer(userService.findOneByCart(cart).get());
		Order order = new Order();
		List<CartItem> cartItems = cartItemService.findAllByCart(cart);
		double totalAmount = 0;
		for (CartItem cartItem : cartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setPrice(cartItem.getPrice());
			orderDto.getOrderItems().add(orderItem);
			totalAmount = (Double) (totalAmount + cartItem.getPrice());
		}
		orderDto.setOrderDate(Calendar.getInstance().getTime());
		orderDto.setTotalAmount(totalAmount);
		BeanUtils.copyProperties(orderDto, order);
		List<OrderItem> orderItems1 = orderDto.getOrderItems();
		for (OrderItem orderItem : orderItems1) {
			orderItem.setOrder(order);
		}
		try {
			orderService.save(order);
			for (CartItem cartItem : cartItems) {
				cartItemService.deleteById(cartItem.getId());
			}
			return new ResponseEntity<>(result.result(false, "Đặt hàng thành công"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
		}

	}

	@GetMapping("/get/{userId}/{statusId}")
	public ResponseEntity<List<Order>> listOrder(@PathVariable("userId") Integer userId,
			@PathVariable("statusId") Integer statusId) {
		List<Order> orders = new ArrayList<>();
		if (statusId == 0) {
			orders = orderService.findByCustomer(userService.findById(userId).get());
		} else {
			orders = orderService.findByCustomerAndOrderStatus(userService.findById(userId).get(),
					orderStatusService.findOneById(statusId).get());
		}

		if (orders.isEmpty()) {
			return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

	@GetMapping(value = "/get-all-by-status/status={status}")
	public ResponseEntity<?> getAllBySatus(@PathVariable("status") int status) {
		List<Order> orders = new ArrayList<>();
		if (status != 0) {
			orders = orderService.findByOrderStatus(orderStatusService.findOneById(status).get());
		} else {
			orders = orderService.findAll();
		}
		if (orders.isEmpty()) {
			return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);

	}

	@GetMapping(value = "/get-all")
	public ResponseEntity<?> getAllOrder() {
		List<Order> orders = new ArrayList<>();
		orders = orderService.findAll();
		if (orders.isEmpty()) {
			return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	}

	@PutMapping(value = "/verify-order/userId={userId}/orderId={orderId}/orderStatus={orderStatus}")
	public ResponseEntity<?> verifyOrder(@PathVariable("userId") Integer userId,
			@PathVariable("orderId") Integer orderId, @PathVariable("orderStatus") Integer orderStatus) {
		Optional<User> userOptional = userService.findById(userId);
		Optional<Order> orderOptional = orderService.findById(orderId);
		Optional<OrderStatus> orderStatusOptional = orderStatusService.findById(orderStatus);
		if (userOptional.isPresent() && orderOptional.isPresent() && orderStatusOptional.isPresent()) {
			Order order = orderOptional.get();
			order.setOrderStatus(orderStatusOptional.get());
			orderService.save(order);
			return new ResponseEntity<>(result.result(false, "Successfully"), HttpStatus.OK);
		}
		return new ResponseEntity<>(result.result(true, "Failed"), HttpStatus.NOT_FOUND);
	}

}
