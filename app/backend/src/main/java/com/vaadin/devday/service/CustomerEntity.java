package com.vaadin.devday.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vaadin.devday.entity.AbstractEntity;

@Entity
@Table(name = "Customer")
public class CustomerEntity extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "Id")
	private Long id;

	@Column(name = "Firstname")
	private String firstName;

	@Column(name = "Lastname")
	private String lastName;

	@Column(name = "BirthDate")
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@OneToMany(mappedBy = "customer")
	private Set<InvoiceEntity> invoices;

	@OneToMany
	@OrderColumn(name = "AddressOrder")
	private List<AddressEntity> addresses;

	public CustomerEntity() {
		super();
	}

	public CustomerEntity(String uuid) {
		super(uuid);
	}

	@Override
	public Long getId() {
		return id;
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

	public Set<InvoiceEntity> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<InvoiceEntity> invoices) {
		this.invoices = invoices;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}
}
