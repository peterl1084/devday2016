package com.vaadin.devday.service;

import com.vaadin.devday.entity.AbstractEntity;
import com.vaadin.devday.service.AbstractDTOWithIdentity;

public interface EntityToDTOConverter {

	AbstractDTOWithIdentity convertToDTO(AbstractEntity entity);

	Class<? extends AbstractEntity> getEntityType();

	Class<? extends AbstractDTOWithIdentity> getDTOType();
}
