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
	private String user_name;
	
	@NotNull
	private String password;
	
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
	private boolean is_deleted;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	private Date last_login;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<Order>orders;
	
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<ProductReview> productReviews;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
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
