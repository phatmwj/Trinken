package com.tp.trinken.model;

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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	private int discount_id;
	
	@NotNull
	private String discount_code;
	
	@Enumerated(EnumType.ORDINAL)
	private DiscountType discount_type;
	
	@NotNull
	private double discount_value;
	
	@NotNull
	private Date start_date;
	
	@NotNull
	private Date end_date;
	
	private int status;
	
	@OneToMany(mappedBy = "discount",cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Product>products;
	

}
