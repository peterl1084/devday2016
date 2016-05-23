package com.vaadin.devday.service.product;

import com.vaadin.devday.service.AbstractDTOWithIdentity;
import com.vaadin.devday.service.TableColumn;

public class ProductListingDTO extends AbstractDTOWithIdentity {

	@TableColumn(name = "Name", group = "Product")
	private String name;
	private String description;

	@TableColumn(name = "Price", group = "Product")
	private Long priceCents;

	public ProductListingDTO() {
		super();
	}

	public ProductListingDTO(String uuid, Long id) {
		super(uuid, id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPriceCents() {
		return priceCents;
	}

	public void setPriceCents(Long priceCents) {
		this.priceCents = priceCents;
	}
}
