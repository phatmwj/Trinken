package com.tp.trinken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tp.trinken.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

}
