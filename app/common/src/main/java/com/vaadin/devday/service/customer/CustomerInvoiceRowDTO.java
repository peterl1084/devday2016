package com.vaadin.devday.service.customer;

import java.math.BigDecimal;

import com.vaadin.devday.service.AbstractDTOWithIdentity;
import com.vaadin.devday.service.TableColumn;

public class CustomerInvoiceRowDTO extends AbstractDTOWithIdentity {

	@TableColumn(name = "Product name", group = "Product")
	private String productName;

	@TableColumn(name = "Pcs", group = "Price")
	private Long numberOfItems;

	@TableColumn(name = "Row price", group = "Price")
	private BigDecimal rowPrice;

	public CustomerInvoiceRowDTO() {
		super();
	}

	public CustomerInvoiceRowDTO(String uuid, Long id) {
		super(uuid, id);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(Long numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public BigDecimal getRowPrice() {
		return rowPrice;
	}

	public void setRowPrice(BigDecimal rowPrice) {
		this.rowPrice = rowPrice;
	}
}
