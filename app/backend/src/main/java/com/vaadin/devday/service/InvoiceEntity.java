package com.vaadin.devday.service;

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
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vaadin.devday.entity.AbstractEntity;

@Entity
@Table(name = "Invoice")
public class InvoiceEntity extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "Id")
	private Long id;

	@Column(name = "Number")
	private Long number;

	@Column(name = "DueDate")
	@Temporal(TemporalType.DATE)
	private Date dueDate;

	@ManyToOne
	@JoinColumn(name = "Customer")
	private CustomerEntity customer;

	@OneToMany(mappedBy = "invoice", cascade = { CascadeType.ALL })
	@OrderColumn(name = "RowOrder")
	private List<InvoiceRowEntity> invoiceRows;

	@ManyToOne
	@JoinColumn(name = "Address")
	private AddressEntity address;

	public InvoiceEntity() {
		super();
	}

	public InvoiceEntity(String uuid) {
		super(uuid);
	}

	@Override
	public Long getId() {
		return id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public List<InvoiceRowEntity> getInvoiceRows() {
		return invoiceRows;
	}

	public void setInvoiceRows(List<InvoiceRowEntity> invoiceRows) {
		this.invoiceRows = invoiceRows;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}
}
