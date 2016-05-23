package com.vaadin.devday.service.customer;

import java.util.List;

import javax.ejb.Local;

@Local
public interface CustomerService {

	List<CustomerInvoiceDTO> getInvoicesForCustomer(long customerId);

	List<CustomerInvoiceRowDTO> getInvoiceRowsForInvoice(long invoiceId);

	int getCustomerInvoiceCount(long customerId);

	int getInvoiceRowCount(long invoiceId);
}
