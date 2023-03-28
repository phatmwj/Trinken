package com.tp.trinken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long>{

}
