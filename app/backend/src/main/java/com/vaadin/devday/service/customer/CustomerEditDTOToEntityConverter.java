package com.vaadin.devday.service.customer;

import javax.ejb.EJB;

import com.vaadin.devday.entity.AbstractEntity;
import com.vaadin.devday.service.AbstractDTOWithIdentity;
import com.vaadin.devday.service.CustomerEntity;
import com.vaadin.devday.service.DTOToEntityConverter;
import com.vaadin.devday.service.DTOType;
import com.vaadin.devday.service.EntityService;

@DTOType(CustomerEditDTO.class)
public class CustomerEditDTOToEntityConverter implements DTOToEntityConverter {

	@EJB
	private EntityService entityService;

	@Override
	public <DTO extends AbstractDTOWithIdentity, E extends AbstractEntity> E convertToEntity(DTO dto) {
		CustomerEditDTO editDto = (CustomerEditDTO) dto;
		CustomerEntity customerEntity = entityService.getEntityById(dto.getId(), CustomerEntity.class);

		customerEntity.setFirstName(editDto.getFirstName());
		customerEntity.setLastName(editDto.getLastName());
		customerEntity.setBirthDate(editDto.getBirthDate());
		return (E) customerEntity;
	}

	@Override
	public Class<? extends AbstractEntity> getEntityType() {
		return CustomerEntity.class;
	}

	@Override
	public Class<? extends AbstractDTOWithIdentity> getDTOType() {
		return CustomerEditDTO.class;
	}
}
