package com.vaadin.devday.service;

import java.util.UUID;

public abstract class AbstractDTOWithIdentity {

	private String uuid;
	private Long id;

	public AbstractDTOWithIdentity() {
		this.uuid = UUID.randomUUID().toString();
	}

	public AbstractDTOWithIdentity(String uuid, Long id) {
		this.uuid = uuid;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getUuid() {
		return uuid;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other instanceof AbstractDTOWithIdentity) {
			return this.getUuid().equals(((AbstractDTOWithIdentity) other).getUuid());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return uuid.hashCode();
	}
}
