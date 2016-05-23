package com.vaadin.devday.service.customer;

import java.math.BigDecimal;
import java.util.Date;

import com.vaadin.devday.service.AbstractDTOWithIdentity;
import com.vaadin.devday.service.TableColumn;

public class CustomerInvoiceDTO extends AbstractDTOWithIdentity {

	@TableColumn(name = "Number", group = "Invoice")
	private Long number;

	@TableColumn(name = "Due date", group = "Invoice")
	private Date dueDate;

	@TableColumn(name = "Total sum", group = "Invoice")
	private BigDecimal totalPriceEur;

	public CustomerInvoiceDTO() {
		super();
	}

	public CustomerInvoiceDTO(String uuid, Long id) {
		super(uuid, id);
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

	public BigDecimal getTotalPriceEur() {
		return totalPriceEur;
	}

	public void setTotalPriceEur(BigDecimal totalPriceEur) {
		this.totalPriceEur = totalPriceEur;
	}
}
