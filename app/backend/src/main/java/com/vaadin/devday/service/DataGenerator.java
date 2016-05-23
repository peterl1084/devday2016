package com.vaadin.devday.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class DataGenerator {

	private final static String[] firstNames = { "Joonas", "Jurka", "Jani", "Marc", "Sami", "Henri", "Ville", "Mikael",
			"Tomi", "Arthur", "Jouni", "Matti", "Jonatan", "Hannu", "Henri", "Teemu", "Johannes", "Risto", "Kim",
			"Jens", "Henrik", "John", "Thomas", "Fredrik", "Teppo", "Sebastian", "Peter", "Tomi", "Petter", "Marcus",
			"Petri", "Anna", "Marlon", "Marko", "Leif", "Samuli", "Rolf", "Pekka", "Tapio", "Haijian", "Miki", "Tanja",
			"Jonni", "Dennis", "Amir", "Patrik", "Michael", "Joacim", "Felyppe", "Artem", "Mac", "Minna", "Sauli",
			"Manolo", "Enver", "Riitta", "Tiina", "Riikka" };

	private final static String[] lastNames = { "Lehtinen", "Rahikkala", "Laakso", "Englund", "Ekblad", "Muurimaa",
			"Ingman", "Vappula", "Virtanen", "Signell", "Koivuviita", "Tahvonen", "Salonen", "Kerola", "Tuikkala",
			"Leppänen", "Jansson", "Paul", "Ahlroos", "Mattson", "Kurki", "Sara", "Nyholm", "Lehto", "Virkki",
			"Holmström", "Hellberg", "Heinonen", "Vesa", "Koskinen", "Richert", "Grönroos", "Häyry", "Åstrand",
			"Kaksonen", "Repo" };

	private final static String[] productNames = { "Coffee mug", "Sun glasses", "Micro USB charger cord", "Gamepad",
			"Marker pen - black", "Marker pen - green", "Marker pen - red", "Soda 0,5l", "Soda 1,5l" };

	@EJB
	private EntityService entityService;

	@PostConstruct
	protected void generateData() {
		// generateCustomerData();
		// generateProductData();
		// generateInvoiceData();
	}

	private void generateCustomerData() {
		Random random = new Random();

		for (int i = 0; i < 1000; i++) {
			String firstName = firstNames[random.nextInt(firstNames.length)];
			String lastName = lastNames[random.nextInt(lastNames.length)];

			LocalDate birthDateCalc = LocalDate.now();
			birthDateCalc = birthDateCalc.withYear(1984).withMonth(1).withDayOfMonth(1);
			birthDateCalc = birthDateCalc.plusDays(random.nextInt(6000));
			Date birthDate = Date.from(birthDateCalc.atStartOfDay(ZoneId.systemDefault()).toInstant());

			CustomerEntity customer = new CustomerEntity();
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setBirthDate(birthDate);

			entityService.storeEntity(customer);
		}
	}

	private void generateProductData() {
		Random random = new Random();

		Arrays.asList(productNames).forEach(productName -> {
			ProductEntity product = new ProductEntity();
			product.setName(productName);
			product.setPriceCents(Long.valueOf(random.nextInt(1000)));

			entityService.storeEntity(product);
		});
	}

	private void generateInvoiceData() {
		Random random = new Random();
		List<CustomerEntity> customers = entityService.getAll(CustomerEntity.class);

		int invoiceNumber = 1;

		List<ProductEntity> allProducts = entityService.getAll(ProductEntity.class);
		for (CustomerEntity customer : customers) {
			int numberOfInvoices = random.nextInt(5);
			for (int i = 0; i < numberOfInvoices; i++) {
				InvoiceEntity invoice = new InvoiceEntity();
				invoice.setCustomer(customer);

				LocalDate dueDateCalc = LocalDate.now();
				dueDateCalc = dueDateCalc.plusDays(random.nextInt(14));
				Date dueDate = Date.from(dueDateCalc.atStartOfDay(ZoneId.systemDefault()).toInstant());
				invoice.setDueDate(dueDate);
				invoice.setNumber(Long.valueOf(invoiceNumber));
				invoiceNumber++;

				int rows = 1 + random.nextInt(5);
				List<InvoiceRowEntity> invoiceRowList = new ArrayList<>();
				for (int z = 0; z < rows; z++) {
					InvoiceRowEntity invoiceRow = new InvoiceRowEntity();
					invoiceRow.setInvoice(invoice);
					invoiceRow.setItems(1 + Long.valueOf(random.nextInt(5)));
					invoiceRow.setProduct(allProducts.get(random.nextInt(allProducts.size())));
					invoiceRowList.add(invoiceRow);
				}
				invoice.setInvoiceRows(invoiceRowList);
				entityService.storeEntity(invoice);
			}
		}
	}
}
