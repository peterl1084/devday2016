package com.vaadin.devday.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {

	@Column(name = "UUID", nullable = false, unique = true)
	private String uuid;

	public AbstractEntity() {
		uuid = UUID.randomUUID().toString();
	}

	public AbstractEntity(String uuid) {
		this.uuid = uuid;
	}

	public abstract Long getId();

	public String getUuid() {
		return uuid;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other instanceof AbstractEntity) {
			return this.getUuid().equals(((AbstractEntity) other).getUuid());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return uuid.hashCode();
	}
}
