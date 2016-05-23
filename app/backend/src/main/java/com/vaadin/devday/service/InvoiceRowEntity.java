package com.vaadin.devday.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vaadin.devday.entity.AbstractEntity;

@Entity
@Table(name = "InvoiceRow")
public class InvoiceRowEntity extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "Id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "Product")
	private ProductEntity product;

	@Column(name = "Items", nullable = false)
	private Long items;

	@ManyToOne
	@JoinColumn(name = "Invoice")
	private InvoiceEntity invoice;

	@Override
	public Long getId() {
		return id;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public Long getItems() {
		return items;
	}

	public void setItems(Long items) {
		this.items = items;
	}

	public InvoiceEntity getInvoice() {
		return invoice;
	}

	public void setInvoice(InvoiceEntity invoice) {
		this.invoice = invoice;
	}
}
