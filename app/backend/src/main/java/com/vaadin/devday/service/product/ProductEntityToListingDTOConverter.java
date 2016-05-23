package com.vaadin.devday.service.product;

import com.vaadin.devday.entity.AbstractEntity;
import com.vaadin.devday.service.AbstractDTOWithIdentity;
import com.vaadin.devday.service.DTOType;
import com.vaadin.devday.service.EntityToDTOConverter;
import com.vaadin.devday.service.ProductEntity;

@DTOType(ProductListingDTO.class)
public class ProductEntityToListingDTOConverter implements EntityToDTOConverter {

	@Override
	public Class<ProductEntity> getEntityType() {
		return ProductEntity.class;
	}

	@Override
	public Class<ProductListingDTO> getDTOType() {
		return ProductListingDTO.class;
	}

	@Override
	public AbstractDTOWithIdentity convertToDTO(AbstractEntity e) {
		ProductEntity entity = (ProductEntity) e;
		ProductListingDTO listingDTO = new ProductListingDTO(entity.getUuid(), entity.getId());
		listingDTO.setName(entity.getName());
		listingDTO.setDescription(entity.getDescription());
		listingDTO.setPriceCents(entity.getPriceCents());

		return listingDTO;
	}
}
