package com.vaadin.devday.ui.view;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.data.Container.Indexed;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.devday.service.AbstractDTOWithIdentity;
import com.vaadin.devday.service.ColumnDefinition;
import com.vaadin.devday.service.ColumnDefinitionUtil;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.SelectionMode;

public abstract class DataTable<DTO extends AbstractDTOWithIdentity, C extends Indexed> extends CustomComponent {
	private static final long serialVersionUID = 194473074237672300L;

	private Grid grid;
	private C container;

	private Class<DTO> dtoType;

	public DataTable(Class<DTO> dtoType) {
		this.dtoType = dtoType;

		setSizeFull();

		grid = new Grid();
		grid.setSizeFull();

		setCompositionRoot(grid);
	}

	public void initialize() {
		container = buildContainer(dtoType);
		grid.setContainerDataSource(container);
		grid.setSelectionMode(SelectionMode.SINGLE);

		List<ColumnDefinition> columnDefinitions = ColumnDefinitionUtil.findColumnDefinitions(dtoType);

		defineColumns(columnDefinitions);
		defineColumnGroups(columnDefinitions);

		applyDateFormatConverters(columnDefinitions);
		applyBigDecimalConverters(columnDefinitions);
	}

	protected Grid getGrid() {
		return grid;
	}

	protected void applyBigDecimalConverters(List<ColumnDefinition> columnDefinitions) {
		columnDefinitions.stream().filter(columnDef -> BigDecimal.class.equals(columnDef.getDataType()))
				.forEach(columnDef -> {
					grid.getColumn(columnDef.getPropertyName()).setConverter(new CurrencyFormatConverter());
				});
	}

	protected void applyDateFormatConverters(List<ColumnDefinition> columnDefinitions) {
		columnDefinitions.stream().filter(columnDef -> Date.class.equals(columnDef.getDataType()))
				.forEach(columnDef -> {
					grid.getColumn(columnDef.getPropertyName()).setConverter(new DateFormatConverter());
				});
	}

	protected void defineColumnGroups(List<ColumnDefinition> columnDefinitions) {
		List<String> columnGroups = columnDefinitions.stream().map(columnDef -> columnDef.getGroup()).distinct()
				.collect(Collectors.toList());

		HeaderRow headerRow = grid.prependHeaderRow();

		for (String columnGroup : columnGroups) {
			List<ColumnDefinition> groupsProperties = columnDefinitions.stream()
					.filter(columnDef -> columnDef.getGroup().equals(columnGroup)).collect(Collectors.toList());
			List<String> propertyIds = groupsProperties.stream().map(columnDef -> columnDef.getPropertyName())
					.collect(Collectors.toList());
			HeaderCell joinedCell = headerRow.join(propertyIds.toArray(new String[propertyIds.size()]));
			joinedCell.setText(columnGroup);
		}
	}

	protected abstract void defineColumns(List<ColumnDefinition> columnDefinitions);

	protected abstract C buildContainer(Class<DTO> dtoType);

	protected C getContainer() {
		return container;
	}

	public void setConverter(Converter<String, ?> converter, String propertyId) {
		grid.getColumn(propertyId).setConverter(converter);
	}

	public void addItemClickListener(ItemClickListener itemClickListener) {
		grid.addItemClickListener(itemClickListener);
	}

	public Long getSelectedRow() {
		return (Long) grid.getSelectedRow();
	}

}
