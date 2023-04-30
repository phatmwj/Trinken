package com.tp.trinken.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.ShippingAddress;

@Repository
public interface ShippingAddressRepo extends JpaRepository<ShippingAddress,Integer>{
	
	List<ShippingAddress> findByUserId(Integer userId);
	
	void deleteById(Integer id);
}
