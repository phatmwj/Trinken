package com.tp.trinken.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp.trinken.entity.Role;
import com.tp.trinken.repository.RoleRepo;
import com.tp.trinken.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepo roleRepo;

	@Override
	public List<Role> findAll() {

		return roleRepo.findAll();
	}

	@Override
	public <S extends Role> S save(S entity) {
		return roleRepo.save(entity);
	}

}
