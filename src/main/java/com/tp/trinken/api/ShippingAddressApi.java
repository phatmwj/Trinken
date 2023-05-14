package com.tp.trinken.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tp.trinken.entity.ShippingAddress;
import com.tp.trinken.entity.User;
import com.tp.trinken.repository.UserRepo;
import com.tp.trinken.service.ShippingAddressService;
import com.tp.trinken.service.UserService;
import com.tp.trinken.utils.Result;

@RestController
@RequestMapping(value = "/shipping-address")
public class ShippingAddressApi {

	@Autowired
	ShippingAddressService shippingAddressService;
	@Autowired
	UserService userService;

	Result result = new Result();

	@PostMapping(value = "/create")
	public ResponseEntity<?> addAddressShipping(@RequestParam Integer userId, @RequestParam String name,
			@RequestParam String phone, @RequestParam String address) {
		ShippingAddress shippingAddress = new ShippingAddress();
		shippingAddress.setAddress(address);
		shippingAddress.setName(name);
		shippingAddress.setPhoneNumber(phone);
		User user = userService.findById(userId).get();
		shippingAddress.setUser(user);
		try {
			shippingAddressService.save(shippingAddress);
			return new ResponseEntity<>(result.resultShippingAddress(false, "Thêm địa chỉ thành công", shippingAddress),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(result.result(true, "Error"), HttpStatus.NOT_IMPLEMENTED);
		}
	}

	@GetMapping(value = "/findByUserId")
	public ResponseEntity<List<ShippingAddress>> findByUserId(@RequestParam Integer id) {
		List<ShippingAddress> shippingAddresses = shippingAddressService.findByUserId(id);
		if (!shippingAddresses.isEmpty()) {
			return new ResponseEntity<List<ShippingAddress>>(shippingAddresses, HttpStatus.OK);
		}
		return new ResponseEntity<List<ShippingAddress>>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		try {
			shippingAddressService.deleteById(id);
			return new ResponseEntity<>(result.result(false, "Đã xóa thành công"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(result.result(true, "Error"), HttpStatus.NOT_IMPLEMENTED);
		}
	}

}
