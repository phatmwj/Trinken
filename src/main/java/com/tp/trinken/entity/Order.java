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
@Table(name="Orders")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="order_date",nullable = false)
	private Date orderDate;
	
	@Column(name="total_amount",nullable = false)
	private double totalAmount;
	
	private Date cancelAt;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private Date completeAt;
	
	private Date deliveryAt;
	
	@ManyToOne
	@JoinColumn(name = "order_status_id")
	@JsonManagedReference
	private OrderStatus orderStatus;
	
	@ManyToOne()
	@JoinColumn(name = "customer_id")
	@JsonManagedReference
	private User customer;
	
	@OneToOne
	@JoinColumn(name = "payment_method_id")
	private PaymentMethod paymentmethod;
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.updatedAt = new Date();
	}
	
		
}
