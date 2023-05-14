package com.tp.trinken.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tp.trinken.entity.PaymentMethod;
import com.tp.trinken.entity.Role;
import com.tp.trinken.service.PaymentMethodService;

@RestController
@RequestMapping("/payment-method")
public class PaymentMethodApi {
	
	@Autowired
	PaymentMethodService paymentMethodService;
	
	@GetMapping("/get-all")
	public ResponseEntity<List<PaymentMethod>> listAllUser() {
		List<PaymentMethod> paymentMethods = paymentMethodService.findAll();
		if (paymentMethods.isEmpty()) {
			return new ResponseEntity<List<PaymentMethod>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PaymentMethod>>(paymentMethods, HttpStatus.OK);
	}

}
