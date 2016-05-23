package com.vaadin.devday.service.customer;

import java.util.Date;

import com.vaadin.devday.service.AbstractDTOWithIdentity;
import com.vaadin.devday.service.TableColumn;

public class CustomerListingDTO extends AbstractDTOWithIdentity {

	@TableColumn(name = "First name", group = "Customer", sort = true)
	private String firstName;

	@TableColumn(name = "Last name", group = "Customer", sort = true)
	private String lastName;

	@TableColumn(name = "Birth date", group = "Customer", sort = true)
	private Date birthDate;

	@TableColumn(name = "Street address", group = "Address", sort = false)
	private String street;

	@TableColumn(name = "City", group = "Address", sort = false)
	private String city;

	@TableColumn(name = "Postal code", group = "Address", sort = false)
	private Long postalCode;

	public CustomerListingDTO() {
		super();
	}

	public CustomerListingDTO(String uuid, Long id) {
		super(uuid, id);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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

}
