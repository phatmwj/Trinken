package com.tp.trinken.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tp.trinken.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	@Query(value = "Select * from users where user_name=? and password=?",nativeQuery = true)
	User verifyUser(String username, String password);
	
	@Query(value="select *from users where user_name=?",nativeQuery = true)
	Optional<User> findByUsername(String username);
	
	@Query(value = "select * from users where email=?",nativeQuery = true)
	Optional<User> findByEmail(String email);
}
