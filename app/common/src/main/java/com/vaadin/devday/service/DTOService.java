package com.vaadin.devday.service;

import java.util.List;

import javax.ejb.Local;

@Local
public interface DTOService {

	<DTO extends AbstractDTOWithIdentity> List<DTO> getAllDtos(Class<DTO> dtoType);

	<DTO extends AbstractDTOWithIdentity> List<DTO> getPagedDtos(Class<DTO> dtoType, int startIndex, int items,
			Object[] sortPropertyIds, boolean[] sortStates);

	<DTO extends AbstractDTOWithIdentity> long getCount(Class<DTO> dtoType);

	<DTO extends AbstractDTOWithIdentity> DTO getDtoById(long itemId, Class<DTO> dtoType);

	<DTO extends AbstractDTOWithIdentity> void storeDto(DTO dto);
}
