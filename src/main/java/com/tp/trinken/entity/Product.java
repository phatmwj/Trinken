package com.tp.trinken.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Products")
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "product_name", unique = true, nullable = false)
	@Nationalized
	private String productName;

	@NotNull
	@Min(value = 0)
	private double price;

	@Nationalized
	private String description;

	private String image;

	@Min(value = 0)
	private int quantity;

	@Min(value = 0)
	private int sold;

	@Column(columnDefinition = "tinyint(1) default 1")
	private boolean active = true;

	private Date deleteAt;

	private Date createdAt;

	private Date updatedAt;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	@JsonManagedReference
	private Brand brand;

//	@ManyToMany(mappedBy = "products")
//	@JsonBackReference
//	private List<Category> categories;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "category_product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	@JsonManagedReference
	private List<Category> categories;

	@ManyToOne
	@JoinColumn(name = "discount_id")
	@JsonManagedReference
	private Discount discount;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Asset> assets;

	@OneToMany(mappedBy = "product")
	@JsonBackReference
	private List<CartItem> cartItems;

	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.updatedAt = new Date();
	}

}
