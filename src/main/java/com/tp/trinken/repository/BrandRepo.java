package com.tp.trinken.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.Brand;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Integer> {

	Boolean existsByBrandName(String name);

	List<Brand> findAllByActive(boolean active);

	Optional<Brand> getByBrandName(String brandName);
}
