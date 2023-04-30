package com.tp.trinken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.Discount;

@Repository
public interface DiscountRepo extends JpaRepository<Discount, Integer> {

}
