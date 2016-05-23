package com.vaadin.devday.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.vaadin.devday.entity.AbstractEntity;

@Entity
@Table(name = "Product")
public class ProductEntity extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "Id")
	private Long id;

	@Column(name = "Name")
	private String name;

	@Column(name = "Description")
	private String description;

	@Lob
	@Column(name = "Image")
	private byte[] image;

	@Column(name = "PriceCents")
	private Long priceCents;

	@Override
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Long getPriceCents() {
		return priceCents;
	}

	public void setPriceCents(Long priceCents) {
		this.priceCents = priceCents;
	}
}
