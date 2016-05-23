package com.vaadin.devday.ui.view.customers;

import java.util.Collections;
import java.util.List;

import org.vaadin.addons.lazyquerycontainer.AbstractBeanQuery;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;

import com.vaadin.devday.service.customer.CustomerInvoiceRowDTO;
import com.vaadin.devday.service.customer.CustomerService;

public class CustomerInvoiceRowQuery extends AbstractBeanQuery<CustomerInvoiceRowDTO> {
	private static final long serialVersionUID = 5792091252496204397L;

	private CustomerService customerService;

	private InvoiceIdCallback invoiceIdCallback;

	public CustomerInvoiceRowQuery(InvoiceIdCallback invoiceIdCallback, CustomerService customerService,
			QueryDefinition queryDefinition) {
		super(queryDefinition, Collections.emptyMap(), queryDefinition.getDefaultSortPropertyIds(),
				queryDefinition.getDefaultSortPropertyAscendingStates());
		this.invoiceIdCallback = invoiceIdCallback;
		this.customerService = customerService;
	}

	@Override
	protected CustomerInvoiceRowDTO constructBean() {
		return new CustomerInvoiceRowDTO();
	}

	@Override
	protected List<CustomerInvoiceRowDTO> loadBeans(int startIndex, int items) {
		return customerService.getInvoiceRowsForInvoice(invoiceIdCallback.getInvoiceId());
	}

	@Override
	protected void saveBeans(List<CustomerInvoiceRowDTO> arg0, List<CustomerInvoiceRowDTO> arg1,
			List<CustomerInvoiceRowDTO> arg2) {
		// nop
	}

	@Override
	public int size() {
		return customerService.getInvoiceRowCount(invoiceIdCallback.getInvoiceId());
	}

	public interface InvoiceIdCallback {
		Long getInvoiceId();
	}
}
