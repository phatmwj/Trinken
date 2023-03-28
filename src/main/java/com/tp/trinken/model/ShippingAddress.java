package com.tp.trinken.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Nationalized;

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
@Table(name="Shipping_Addresses")
public class ShippingAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int address_id;
	
	@NotNull
	@Nationalized
	private String name;
	
	@NotNull
	private String phone_number;
	
	@NotNull
	@Nationalized
	private String address;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean is_deleted;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonManagedReference
	private User user;
	
	@PrePersist
	void createdAt() {
		this.createdAt = this.updatedAt = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.updatedAt = new Date();
	}

}
