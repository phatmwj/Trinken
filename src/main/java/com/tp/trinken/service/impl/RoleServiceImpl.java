package com.tp.trinken.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.model.Role;
import com.tp.trinken.repository.RoleRepo;
import com.tp.trinken.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepo roleRepo;

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepo.findAll();
	}
	


}
