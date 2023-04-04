package com.tp.trinken.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Nationalized;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="Product_Reviews")
public class ProductReview implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Min(value = 0)
	private int rating;
	
	@NotNull
	@Nationalized
	private String comment;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private boolean active = true;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	@OneToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne()
	@JoinColumn(name = "customer_id")
	private User customer;
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.updatedAt = new Date();
	}

}
