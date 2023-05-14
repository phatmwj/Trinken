package com.tp.trinken.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.Cart;
import com.tp.trinken.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	Optional<User> findOneByUserName(String username);
	
	Optional<User> findOneByEmail(String email);
	
    Boolean existsByEmail(String email);
    
    Boolean existsByUserName(String username);
    
    Optional<User> findOneByUserNameAndPassword(String username,String password);
    
    Optional<User>findOneByCart(Cart cart);
}
