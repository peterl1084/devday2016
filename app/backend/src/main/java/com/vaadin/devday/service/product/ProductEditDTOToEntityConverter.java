package com.vaadin.devday.service.product;

import javax.ejb.EJB;

import com.vaadin.devday.entity.AbstractEntity;
import com.vaadin.devday.service.AbstractDTOWithIdentity;
import com.vaadin.devday.service.CustomerEntity;
import com.vaadin.devday.service.DTOToEntityConverter;
import com.vaadin.devday.service.DTOType;
import com.vaadin.devday.service.EntityService;
import com.vaadin.devday.service.ProductEntity;
import com.vaadin.devday.service.customer.CustomerEditDTO;

@DTOType(ProductEditDTO.class)
public class ProductEditDTOToEntityConverter implements DTOToEntityConverter {

	@EJB
	private EntityService entityService;

	@Override
	public <DTO extends AbstractDTOWithIdentity, E extends AbstractEntity> E convertToEntity(DTO dto) {
		ProductEditDTO editDto = (ProductEditDTO) dto;
		ProductEntity productEntity = entityService.getEntityById(dto.getId(), ProductEntity.class);

		productEntity.setName(editDto.getName());
		productEntity.setDescription(editDto.getDescription());
		productEntity.setPriceCents(editDto.getPriceCents());
		return (E) productEntity;
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
