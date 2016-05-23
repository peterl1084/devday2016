package com.vaadin.devday.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vaadin.devday.entity.AbstractEntity;

@Entity
@Table(name = "Address")
public class AddressEntity extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "Id")
	private Long id;

	@Column(name = "Street")
	private String street;

	@Column(name = "City")
	private String city;

	@Column(name = "PostalCode")
	private Long postalCode;

	@Column(name = "Country")
	private String country;

	@Override
	public Long getId() {
		return id;
	}

	public AddressEntity() {
		super();
	}

	public AddressEntity(String uuid) {
		super(uuid);
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Long postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
