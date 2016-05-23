package com.vaadin.devday.ui.view;

import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.devday.service.AbstractDTOWithIdentity;
import com.vaadin.devday.service.ColumnDefinition;

public class BeanDataTable<DTO extends AbstractDTOWithIdentity> extends DataTable<DTO, BeanContainer<Long, DTO>> {
	private static final long serialVersionUID = 1053109164193130974L;

	public BeanDataTable(Class<DTO> dtoType) {
		super(dtoType);

		initialize();
	}

	@Override
	protected BeanContainer<Long, DTO> buildContainer(Class<DTO> dtoType) {
		BeanContainer<Long, DTO> beanContainer = new BeanContainer<>(dtoType);
		beanContainer.setBeanIdProperty("id");
		return beanContainer;
	}

	public void setDataItems(List<DTO> dataItems) {
		getContainer().removeAllItems();
		dataItems.forEach(dataItem -> getContainer().addBean(dataItem));
	}

	@Override
	protected void defineColumns(List<ColumnDefinition> columnDefinitions) {
		List<String> columnsToShow = columnDefinitions.stream().map(columnDef -> columnDef.getPropertyName())
				.collect(Collectors.toList());
		getGrid().setColumns(columnsToShow.toArray(new String[columnsToShow.size()]));
	}
}
