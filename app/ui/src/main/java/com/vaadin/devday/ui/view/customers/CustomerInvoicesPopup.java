package com.vaadin.devday.ui.view.customers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;

import com.vaadin.devday.service.customer.CustomerInvoiceDTO;
import com.vaadin.devday.service.customer.CustomerService;
import com.vaadin.devday.ui.NavigationManager;
import com.vaadin.devday.ui.view.AbstractPopupEditor;
import com.vaadin.devday.ui.view.LazyDataTable;
import com.vaadin.devday.ui.view.customers.CustomerInvoiceQuery.CustomerIdCallback;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.VerticalLayout;

public class CustomerInvoicesPopup extends AbstractPopupEditor<CustomerInvoiceDTO> implements CustomerIdCallback {
	private static final long serialVersionUID = 2019159352064727884L;

	public CustomerInvoicesPopup() {
		super(new VerticalLayout(), CustomerInvoiceDTO.class);
		setReadOnly(true);
	}

	private LazyDataTable<CustomerInvoiceDTO> invoiceTable;

	@EJB
	private CustomerService customerService;

	@Inject
	private NavigationManager navigationManager;

	private Long customerId;

	@PostConstruct
	protected void initialize() {
		invoiceTable = new LazyDataTable<>(CustomerInvoiceDTO.class,
				(definition) -> new CustomerInvoiceQuery(this, customerService, definition));
		addComponent(invoiceTable);
		invoiceTable.addItemClickListener(e -> onInvoiceClicked(e));
	}

	private void onInvoiceClicked(ItemClickEvent e) {
		CustomerInvoiceRowsPopup rowPopup = navigationManager.showInPopup(CustomerInvoiceRowsPopup.class);
		rowPopup.setInvoiceId((Long) e.getItemId());
	}

	@Override
	public String getWindowCaption() {
		return "Customer's invoices";
	}

	@Override
	public void focusFirst() {

	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public Long getCustomerId() {
		return customerId;
	}
}
