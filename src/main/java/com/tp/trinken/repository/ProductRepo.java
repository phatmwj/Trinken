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

	@Query(value = "select p.* from products p inner join category_product c "
			+ "on p.id = c.product_id where p.active = :active and c.category_id = :category_id order by p.price ASC", nativeQuery = true)
	List<Product> findAllProductByCategoryAndActiveOrderByPriceAsc(Integer active, Integer category_id);

	@Query(value = "select p.* from products p inner join category_product c "
			+ "on p.id = c.product_id where p.active = :active and c.category_id = :category_id order by p.price DESC", nativeQuery = true)
	List<Product> findAllProductByCategoryAndActiveOrderByPriceDesc(Integer active, Integer category_id);

	@Query(value = "select p.* from products p inner join category_product c \r\n"
			+ "	on p.id = c.product_id where p.active = :active and c.category_id = :category_id order by p.id ASC", nativeQuery = true)
	List<Product> findAllProductByCategoryIdAndActiveOrderByIdAsc(Integer active, Integer category_id);

	@Query(value = "select p.* from products p inner join category_product c \r\n"
			+ "	on p.id = c.product_id where p.active = :active and c.category_id = :category_id order by p.id DESC", nativeQuery = true)
	List<Product> findAllProductByCategoryIdAndActiveOrderByIdDesc(Integer active, Integer category_id);

	@Query(value = "select p.* from products p inner join category_product c \r\n"
			+ "	on p.id = c.product_id where p.active = :active and c.category_id = :category_id order by p.product_name ASC", nativeQuery = true)
	List<Product> findAllProductByCategoryIdAndActiveOrderByProductNameAsc(Integer active, Integer category_id);

	@Query(value = "select p.* from products p inner join category_product c \r\n"
			+ "	on p.id = c.product_id where p.active = :active and c.category_id = :category_id order by p.product_name DESC", nativeQuery = true)
	List<Product> findAllProductByCategoryIdAndActiveOrderByProductNameDesc(Integer active, Integer category_id);

	@Query(value = "select p.* from products p inner join category_product c \r\n"
			+ "	on p.id = c.product_id where p.active = :active and c.category_id = :category_id order by p.sold ASC", nativeQuery = true)
	List<Product> findAllProductByCategoryIdAndActiveOrderByProductSoldAsc(Integer active, Integer category_id);

	@Query(value = "select p.* from products p inner join category_product c \r\n"
			+ "	on p.id = c.product_id where p.active = :active and c.category_id = :category_id order by p.sold DESC", nativeQuery = true)
	List<Product> findAllProductByCategoryIdAndActiveOrderByProductSoldDesc(Integer active, Integer category_id);
}
