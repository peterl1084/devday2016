package com.vaadin.devday.ui.view.customers;

import java.util.Collections;
import java.util.List;

import org.vaadin.addons.lazyquerycontainer.AbstractBeanQuery;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;

import com.vaadin.devday.service.customer.CustomerInvoiceDTO;
import com.vaadin.devday.service.customer.CustomerService;

public class CustomerInvoiceQuery extends AbstractBeanQuery<CustomerInvoiceDTO> {
	private static final long serialVersionUID = 4377290655124073557L;

	private CustomerService customerService;

	private CustomerIdCallback customerIdCallback;

	public CustomerInvoiceQuery(CustomerIdCallback customerIdCallback, CustomerService customerService,
			QueryDefinition queryDefinition) {
		super(queryDefinition, Collections.emptyMap(), queryDefinition.getDefaultSortPropertyIds(),
				queryDefinition.getDefaultSortPropertyAscendingStates());
		this.customerIdCallback = customerIdCallback;
		this.customerService = customerService;
	}

	@Override
	protected CustomerInvoiceDTO constructBean() {
		return new CustomerInvoiceDTO();
	}

	@Override
	protected List<CustomerInvoiceDTO> loadBeans(int startIndex, int items) {
		return customerService.getInvoicesForCustomer(customerIdCallback.getCustomerId());
	}

	@Override
	protected void saveBeans(List<CustomerInvoiceDTO> arg0, List<CustomerInvoiceDTO> arg1,
			List<CustomerInvoiceDTO> arg2) {
		// nop
	}

	@Override
	public int size() {
		return customerService.getCustomerInvoiceCount(customerIdCallback.getCustomerId());
	}

	public interface CustomerIdCallback {
		Long getCustomerId();
	}
}
