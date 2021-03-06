package com.vaadin.devday.service.customer;

import com.vaadin.devday.entity.AbstractEntity;
import com.vaadin.devday.service.AbstractDTOWithIdentity;
import com.vaadin.devday.service.CustomerEntity;
import com.vaadin.devday.service.DTOType;
import com.vaadin.devday.service.EntityToDTOConverter;

@DTOType(CustomerListingDTO.class)
public class CustomerEntityToListingDTOConverter implements EntityToDTOConverter {

	@Override
	public Class<CustomerEntity> getEntityType() {
		return CustomerEntity.class;
	}

	@Override
	public Class<CustomerListingDTO> getDTOType() {
		return CustomerListingDTO.class;
	}

	@Override
	public AbstractDTOWithIdentity convertToDTO(AbstractEntity e) {
		CustomerEntity entity = (CustomerEntity) e;
		CustomerListingDTO listingDTO = new CustomerListingDTO(entity.getUuid(), entity.getId());
		listingDTO.setFirstName(entity.getFirstName());
		listingDTO.setLastName(entity.getLastName());
		listingDTO.setBirthDate(entity.getBirthDate());

		return listingDTO;
	}
}
