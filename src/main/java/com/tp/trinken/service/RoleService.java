package com.tp.trinken.service;

import java.util.List;
import java.util.Optional;

import com.tp.trinken.entity.Role;

public interface RoleService {

	List<Role> findAll();

	<S extends Role> S save(S entity);

	Optional<Role> findById(int id);

}
