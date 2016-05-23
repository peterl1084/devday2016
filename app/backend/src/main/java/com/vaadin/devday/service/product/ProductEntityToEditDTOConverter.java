package com.vaadin.devday.service.product;

import com.vaadin.devday.entity.AbstractEntity;
import com.vaadin.devday.service.AbstractDTOWithIdentity;
import com.vaadin.devday.service.DTOType;
import com.vaadin.devday.service.EntityToDTOConverter;
import com.vaadin.devday.service.ProductEntity;

@DTOType(ProductEditDTO.class)
public class ProductEntityToEditDTOConverter implements EntityToDTOConverter {

	@Override
	public Class<ProductEntity> getEntityType() {
		return ProductEntity.class;
	}

	@Override
	public Class<ProductEditDTO> getDTOType() {
		return ProductEditDTO.class;
	}

	@Override
	public AbstractDTOWithIdentity convertToDTO(AbstractEntity e) {
		ProductEntity entity = (ProductEntity) e;
		ProductEditDTO productDTO = new ProductEditDTO(entity.getUuid(), entity.getId());
		productDTO.setName(entity.getName());
		productDTO.setDescription(entity.getDescription());
		productDTO.setPriceCents(entity.getPriceCents());

		return productDTO;
	}
}
