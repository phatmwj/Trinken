package com.tp.trinken.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="Discounts")
public class Discount implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="discount_code",nullable = false)
	private String discountCode;
	
	@Column(name="discount_type")
	@Enumerated(EnumType.ORDINAL)
	private DiscountType discountType;

	@Column(name="discount_value",nullable = false)
	private double discountValue;

	@Column(name="start_date",nullable = false)
	private Date startDate;

	@Column(name="end_date",nullable = false)
	private Date endDate;
	
	private int status;
	
	@OneToMany(mappedBy = "discount",cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Product>products;
	

}
