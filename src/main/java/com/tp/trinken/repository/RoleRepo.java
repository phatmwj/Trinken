package com.tp.trinken.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
	Optional<Role> findById(int id);
}
