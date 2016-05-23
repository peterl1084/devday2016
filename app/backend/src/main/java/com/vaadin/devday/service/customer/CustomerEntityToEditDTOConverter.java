package com.vaadin.devday.service.customer;

import com.vaadin.devday.entity.AbstractEntity;
import com.vaadin.devday.service.AbstractDTOWithIdentity;
import com.vaadin.devday.service.CustomerEntity;
import com.vaadin.devday.service.DTOType;
import com.vaadin.devday.service.EntityToDTOConverter;

@DTOType(CustomerEditDTO.class)
public class CustomerEntityToEditDTOConverter implements EntityToDTOConverter {

	@Override
	public Class<CustomerEntity> getEntityType() {
		return CustomerEntity.class;
	}

	@Override
	public Class<CustomerEditDTO> getDTOType() {
		return CustomerEditDTO.class;
	}

	@Override
	public AbstractDTOWithIdentity convertToDTO(AbstractEntity e) {
		CustomerEntity entity = (CustomerEntity) e;
		CustomerEditDTO editDTO = new CustomerEditDTO(entity.getUuid(), entity.getId());
		editDTO.setFirstName(entity.getFirstName());
		editDTO.setLastName(entity.getLastName());
		editDTO.setBirthDate(entity.getBirthDate());

		return editDTO;
	}
}
