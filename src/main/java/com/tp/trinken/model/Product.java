package com.tp.trinken.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Nationalized;
import org.springframework.beans.factory.annotation.Value;

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
@Table(name="Products")
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	
	@Column(unique = true,nullable = false)
	@Nationalized
	private String product_name;
	
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
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean active;
	
	private Date deleteAt;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	@JsonManagedReference
	private Brand brand;

	@ManyToMany(mappedBy = "products")
	@JsonBackReference
	private List<Category> categories;
	
	@ManyToOne
	@JoinColumn(name = "discount_id")
	@JsonManagedReference
	private Discount discount;
	
	@OneToMany(mappedBy = "product")
	@JsonBackReference
	private List<Asset>assets;
	
	@OneToMany(mappedBy = "product")
	@JsonBackReference
	private List<CartItem> cartItems;
	
	@PrePersist
	void createdAt() {
		this.createdAt = this.updatedAt = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.updatedAt = new Date();
	}
	
}
