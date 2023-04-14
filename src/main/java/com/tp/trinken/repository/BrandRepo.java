package com.tp.trinken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.Brand;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Integer> {

	Boolean existsByBrandName(String name);
}
