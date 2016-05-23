package com.vaadin.devday.ui.view.customers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import com.vaadin.devday.service.customer.CustomerInvoiceRowDTO;
import com.vaadin.devday.service.customer.CustomerService;
import com.vaadin.devday.ui.view.AbstractPopupEditor;
import com.vaadin.devday.ui.view.LazyDataTable;
import com.vaadin.devday.ui.view.customers.CustomerInvoiceRowQuery.InvoiceIdCallback;
import com.vaadin.ui.VerticalLayout;

public class CustomerInvoiceRowsPopup extends AbstractPopupEditor<CustomerInvoiceRowDTO> implements InvoiceIdCallback {
	private static final long serialVersionUID = 2019159352064727884L;

	public CustomerInvoiceRowsPopup() {
		super(new VerticalLayout(), CustomerInvoiceRowDTO.class);
		setReadOnly(true);
	}

	private LazyDataTable<CustomerInvoiceRowDTO> invoiceTable;

	@EJB
	private CustomerService customerService;

	private Long invoiceId;

	@PostConstruct
	protected void initialize() {
		invoiceTable = new LazyDataTable<>(CustomerInvoiceRowDTO.class,
				(definition) -> new CustomerInvoiceRowQuery(this, customerService, definition));
		addComponent(invoiceTable);
	}

	@Override
	public String getWindowCaption() {
		return "Customer's invoices";
	}

	@Override
	public void focusFirst() {

	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	@Override
	public Long getInvoiceId() {
		return invoiceId;
	}
}
