package com.vaadin.devday.service.customer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.vaadin.devday.service.CustomerEntity;
import com.vaadin.devday.service.EntityService;
import com.vaadin.devday.service.InvoiceEntity;
import com.vaadin.devday.service.InvoiceRowEntity;

@Stateless
public class CustomerServiceBean implements CustomerService {

	@PersistenceContext(unitName = "devday")
	private EntityManager entityManager;

	@EJB
	private EntityService entityService;

	@Override
	public List<CustomerInvoiceDTO> getInvoicesForCustomer(long customerId) {
		CustomerEntity customerEntity = entityService.getEntityById(customerId, CustomerEntity.class);

		List<InvoiceEntity> customerInvoices = entityManager
				.createQuery("SELECT e FROM " + InvoiceEntity.class.getSimpleName() + " e WHERE e.customer = :customer",
						InvoiceEntity.class)
				.setParameter("customer", customerEntity).getResultList();

		List<CustomerInvoiceDTO> invoices = customerInvoices.stream().map(invoice -> wrapToDTO(invoice))
				.collect(Collectors.toList());
		return invoices;
	}

	@Override
	public List<CustomerInvoiceRowDTO> getInvoiceRowsForInvoice(long invoiceId) {
		InvoiceEntity invoiceEntity = entityService.getEntityById(invoiceId, InvoiceEntity.class);

		List<InvoiceRowEntity> invoiceRows = entityManager.createQuery(
				"SELECT e FROM " + InvoiceRowEntity.class.getSimpleName() + " e WHERE e.invoice = :invoice",
				InvoiceRowEntity.class).setParameter("invoice", invoiceEntity).getResultList();

		List<CustomerInvoiceRowDTO> invoices = invoiceRows.stream().map(invoice -> wrapToInvoiceRowDTO(invoice))
				.collect(Collectors.toList());
		return invoices;
	}

	private CustomerInvoiceDTO wrapToDTO(InvoiceEntity invoice) {
		CustomerInvoiceDTO invoiceDTO = new CustomerInvoiceDTO(invoice.getUuid(), invoice.getId());
		invoiceDTO.setDueDate(invoice.getDueDate());
		invoiceDTO.setNumber(invoice.getNumber());

		BigDecimal totalSumCents = BigDecimal.ZERO;
		for (InvoiceRowEntity row : invoice.getInvoiceRows()) {
			BigDecimal productPriceCents = BigDecimal.valueOf(row.getProduct().getPriceCents());
			BigDecimal totalPriceCents = productPriceCents.multiply(BigDecimal.valueOf(row.getItems()));
			totalSumCents = totalSumCents.add(totalPriceCents);
		}

		BigDecimal totalPriceEur = totalSumCents.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

		invoiceDTO.setTotalPriceEur(totalPriceEur);
		return invoiceDTO;
	}

	private CustomerInvoiceRowDTO wrapToInvoiceRowDTO(InvoiceRowEntity invoiceRow) {
		CustomerInvoiceRowDTO invoiceRowDTO = new CustomerInvoiceRowDTO(invoiceRow.getUuid(), invoiceRow.getId());
		invoiceRowDTO.setNumberOfItems(invoiceRow.getItems());
		invoiceRowDTO.setProductName(invoiceRow.getProduct().getName());

		BigDecimal productPriceCents = BigDecimal.valueOf(invoiceRow.getProduct().getPriceCents());
		BigDecimal totalPriceCents = productPriceCents.multiply(BigDecimal.valueOf(invoiceRow.getItems()));

		BigDecimal totalPriceEur = totalPriceCents.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

		invoiceRowDTO.setRowPrice(totalPriceEur);
		return invoiceRowDTO;
	}

	@Override
	public int getCustomerInvoiceCount(long customerId) {
		CustomerEntity customer = entityService.getEntityById(customerId, CustomerEntity.class);

		int invoices = entityManager
				.createQuery("SELECT COUNT(e.id) FROM " + InvoiceEntity.class.getSimpleName()
						+ " e WHERE e.customer = :customer", Long.class)
				.setParameter("customer", customer).getSingleResult().intValue();
		return invoices;
	}

	@Override
	public int getInvoiceRowCount(long invoiceId) {
		InvoiceEntity invoice = entityService.getEntityById(invoiceId, InvoiceEntity.class);

		int rows = entityManager
				.createQuery("SELECT COUNT(e.id) FROM " + InvoiceRowEntity.class.getSimpleName()
						+ " e WHERE e.invoice = :invoice", Long.class)
				.setParameter("invoice", invoice).getSingleResult().intValue();
		return rows;
	}
}
