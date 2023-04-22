package com.tp.trinken.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

	/*
	 * @Query(value = "UPDATE Categories SET active = 0 WHERE id = ?1", nativeQuery
	 * = true) Integer setUnActiceCategory(Integer id);
	 */

	List<Category> findByActive(boolean active);

	Boolean existsByCategoryName(String name);

	List<Category> findByCategoryName(String categoryName);
}
