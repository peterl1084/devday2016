package com.vaadin.devday.service;

import com.vaadin.devday.entity.AbstractEntity;

public interface DTOToEntityConverter {

	<DTO extends AbstractDTOWithIdentity, E extends AbstractEntity> E convertToEntity(DTO dto);

	Class<? extends AbstractEntity> getEntityType();

	Class<? extends AbstractDTOWithIdentity> getDTOType();

}
