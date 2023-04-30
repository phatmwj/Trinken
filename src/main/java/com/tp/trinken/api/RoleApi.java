package com.tp.trinken.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tp.trinken.entity.Role;
import com.tp.trinken.service.RoleService;
import com.tp.trinken.service.UserService;
import com.tp.trinken.utils.Result;

@RestController
@RequestMapping("role")
public class RoleApi {
	@Autowired
	RoleService roleService;

	@Autowired
	UserService userService;

	Result result;

	@GetMapping("/get-all")
	public ResponseEntity<List<Role>> listAllUser() {
		List<Role> listRoles = roleService.findAll();
		if (listRoles.isEmpty()) {
			return new ResponseEntity<List<Role>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Role>>(listRoles, HttpStatus.OK);
	}

	@PostMapping(value = "/create/userid={id}")
	public ResponseEntity<?> createNewRole(@RequestBody Role role, @RequestParam int id) {
		if (userService.findById(id).get() != null) {
			roleService.save(role);
			return new ResponseEntity<>(result.result(false, "Created successfully!"), HttpStatus.OK);
		}
		return new ResponseEntity<>(result.result(true, "Failed!"), HttpStatus.NOT_ACCEPTABLE);
	}
}
