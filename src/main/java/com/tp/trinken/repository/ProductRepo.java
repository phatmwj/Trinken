package com.tp.trinken.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tp.trinken.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	Boolean existsByProductName(String productName);

	List<Product> findByActive(boolean active);

	@Query(value = "select p.* from products p inner join category_product c "
			+ "on p.id = c.product_id where p.active = ?1 and c.category_id = ?2", nativeQuery = true)
	List<Product> getProductByCategoryAndActive(Integer active, Integer category_id);

}
