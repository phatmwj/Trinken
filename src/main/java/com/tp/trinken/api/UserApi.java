package com.tp.trinken.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tp.trinken.model.User;
import com.tp.trinken.service.UserService;
import com.tp.trinken.utils.Result;

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
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password ) throws IOException{
		User user=userService.login(username, password);
		user.getPassword();
		Map<String , Object> map= new HashMap<String,Object>();
		
		if(user==null){
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new Result(false,"Sai thong tin dang nhap",null));
		}
		else {
			map.put("error", false);
			map.put("message","Dang nhap thanh cong");
			map.put("user", user);
			return ResponseEntity.status(HttpStatus.OK).body(map);
		}
	}
	
}
