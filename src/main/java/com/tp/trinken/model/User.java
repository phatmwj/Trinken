package com.tp.trinken.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="Users")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	
	@Column(unique = true,nullable = false)
	@Size(max = 32)
	private String user_name;
	
	@NotNull
	@JsonIgnore
	private String password;
	
	@Nationalized
	@Size(max = 32)
	private String first_name;
	
	@Nationalized
	@Size(max = 32)
	private String last_name;
	
	@Nationalized
	private String gender;
	
	@Column(unique = true, nullable = false)
	@Nationalized
	private String email;
	
	@Column(unique = true, nullable = false)
	private String phone_number;
	
	@NotNull
	@Nationalized
	private String address;
	
	private String image;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean active;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private Date last_login;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	@JsonManagedReference
	private Role role;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Order>orders;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	@JsonBackReference
	private List<ProductReview> productReviews;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	@JsonBackReference
	private List<ShippingAddress> shippingAddresses;
	
	@PrePersist
	void createdAt() {
		this.createdAt = this.updatedAt = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.updatedAt = new Date();
	}
}
