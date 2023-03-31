package com.tp.trinken.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
@Table(name="Order_Items")
public class OrderItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_item_id;
	
	@NotNull
	@Min(value = 1)
	private int quantity;
	
	private double price;
	
	@OneToOne
	@JoinColumn(name="product_id")
	@NotNull
	@JsonManagedReference
	private Product product;
	
	@OneToOne
	@JoinColumn(name = "order_id")
	@JsonManagedReference
	private Order order;

}
