package com.vaadin.devday.service.product;

import com.vaadin.devday.service.AbstractDTOWithIdentity;

public class ProductEditDTO extends AbstractDTOWithIdentity {

	private String name;
	private String description;
	private Long priceCents;

	public ProductEditDTO() {
		super();
	}

	public ProductEditDTO(String uuid, Long id) {
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
