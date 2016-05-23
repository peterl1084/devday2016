package com.vaadin.devday.service;

import javax.enterprise.util.AnnotationLiteral;

import com.vaadin.devday.entity.AbstractEntity;

public class EntityTypeAnnotationLiteral extends AnnotationLiteral<EntityType> implements EntityType {
	private static final long serialVersionUID = 8878437029291748787L;

	private Class<? extends AbstractEntity> entityType;

	public EntityTypeAnnotationLiteral(Class<? extends AbstractEntity> entityType) {
		this.entityType = entityType;
	}

	@Override
	public Class<? extends AbstractEntity> value() {
		return entityType;
	}
}
