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
}
