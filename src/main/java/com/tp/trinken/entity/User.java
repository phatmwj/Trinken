package com.tp.trinken.entity;

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
import lombok.Builder;
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
	private int id;
	
	@Column(name="user_name",unique = true,nullable = false)
	@Size(max = 32)
	private String userName;

	@JsonIgnore
	@NotNull
	private String password;
	
	@Nationalized
	@Size(max = 32)
	@Column(name="first_name")
	private String firstName;
	
	@Nationalized
	@Size(max = 32)
	@Column(name="last_name")
	private String lastName;
	
	@Nationalized
	private String gender;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(name="phone_number",unique = true, nullable = true)
	private String phoneNumber;

	@Nationalized
	private String address;
	
	private String image;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private boolean active = true;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	@Column(name="last_login")
	private Date lastLogin;
	
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
		this.createdAt = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.updatedAt = new Date();
	}
}
