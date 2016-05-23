package com.vaadin.devday.ui.view;

import java.util.Collections;
import java.util.List;

import org.vaadin.addons.lazyquerycontainer.AbstractBeanQuery;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;

import com.vaadin.devday.service.AbstractDTOWithIdentity;
import com.vaadin.devday.service.DTOService;

public class LazyDTOQuery<DTO extends AbstractDTOWithIdentity> extends AbstractBeanQuery<DTO> {
	private static final long serialVersionUID = -1187168418650625396L;

	private Class<DTO> dtoType;

	private DTOService dtoService;

	public LazyDTOQuery(Class<DTO> dtoType, DTOService dtoService, QueryDefinition queryDefinition) {
		super(queryDefinition, Collections.emptyMap(), queryDefinition.getSortPropertyIds(),
				queryDefinition.getSortPropertyAscendingStates());

		this.dtoType = dtoType;
		this.dtoService = dtoService;
	}

	@Override
	protected DTO constructBean() {
		try {
			return dtoType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException("Failed to instantiate new DTO with type " + dtoType.getSimpleName(), e);
		}
	}

	@Override
	protected List<DTO> loadBeans(int startIndex, int items) {
		return dtoService.getPagedDtos(dtoType, startIndex, items, getSortPropertyIds(), getSortStates());
	}

	@Override
	protected void saveBeans(List<DTO> arg0, List<DTO> arg1, List<DTO> arg2) {
		// nop
	}

	@Override
	public int size() {
		return (int) dtoService.getCount(dtoType);
	}
}
