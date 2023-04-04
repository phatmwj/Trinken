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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

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
@Table(name="Brands")

public class Brand implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="brand_name",unique = true,nullable = false)
	@Nationalized
	private String brandName;
	
	private String image;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private boolean active = true;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	@OneToMany(mappedBy ="brand",cascade = CascadeType.ALL )
	@JsonBackReference
	private List<Product> product;
	
	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}

	@PreUpdate
	void updatedAt() {
		this.updatedAt = new Date();
	}
}
