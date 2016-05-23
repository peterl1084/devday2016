package com.vaadin.devday.service.customer;

import java.util.Date;

import com.vaadin.devday.service.AbstractDTOWithIdentity;

public class CustomerEditDTO extends AbstractDTOWithIdentity {

	private String firstName;
	private String lastName;
	private Date birthDate;

	public CustomerEditDTO() {
		super();
	}

	public CustomerEditDTO(String uuid, Long id) {
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
}
