package com.tp.trinken.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tp.trinken.entity.Role;
import com.tp.trinken.service.RoleService;

@RestController
@RequestMapping("role")
public class RoleApi {
	@Autowired
	RoleService roleService;
	
	@RequestMapping(value = "/get-all", method = RequestMethod.GET)
	public ResponseEntity<List<Role>> listAllUser(){
		List<Role> listRoles= roleService.findAll();
		if(listRoles.isEmpty()) {
			return new ResponseEntity<List<Role>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Role>>(listRoles, HttpStatus.OK);
	}
}
