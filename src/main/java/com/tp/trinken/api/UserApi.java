package com.tp.trinken.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tp.trinken.model.User;
import com.tp.trinken.service.UserService;

@RestController
@RequestMapping("/user")
public class UserApi {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/get-all", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUser(){
		List<User> listUsers= userService.findAll();
		if(listUsers.isEmpty()) {
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(listUsers, HttpStatus.OK);
	}
}
