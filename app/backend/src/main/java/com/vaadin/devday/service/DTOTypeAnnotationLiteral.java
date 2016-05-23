package com.vaadin.devday.service;

import javax.enterprise.util.AnnotationLiteral;

import com.vaadin.devday.service.AbstractDTOWithIdentity;

public class DTOTypeAnnotationLiteral extends AnnotationLiteral<DTOType> implements DTOType {
	private static final long serialVersionUID = -793026317442164775L;

	private Class<? extends AbstractDTOWithIdentity> dtoType;

	public DTOTypeAnnotationLiteral(Class<? extends AbstractDTOWithIdentity> dtoType) {
		this.dtoType = dtoType;
	}

	@Override
	public Class<? extends AbstractDTOWithIdentity> value() {
		return dtoType;
	}
}
