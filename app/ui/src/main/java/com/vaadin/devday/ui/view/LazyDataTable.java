package com.vaadin.devday.ui.view;

import java.util.List;

import org.vaadin.addons.lazyquerycontainer.LazyQueryContainer;
import org.vaadin.addons.lazyquerycontainer.QueryFactory;

import com.vaadin.devday.service.AbstractDTOWithIdentity;
import com.vaadin.devday.service.ColumnDefinition;
import com.vaadin.ui.Grid.Column;

/**
 * LazyDataTable is DataTable implementation that internally utilizes
 * LazyQueryContainer for fetching data in chunks from backend services.
 * 
 * @author Peter / Vaadin
 */
public class LazyDataTable<DTO extends AbstractDTOWithIdentity> extends DataTable<DTO, LazyQueryContainer> {
	private static final long serialVersionUID = 194473074237672300L;

	private QueryFactory queryFactory;

	public LazyDataTable(Class<DTO> dtoType, QueryFactory queryFactory) {
		super(dtoType);
		this.queryFactory = queryFactory;

		initialize();
	}

	public void refresh() {
		getContainer().refresh();
	}

	@Override
	protected LazyQueryContainer buildContainer(Class<DTO> dtoType) {
		return new LazyQueryContainer(queryFactory, "id", 50, false);
	}

	@Override
	protected void defineColumns(List<ColumnDefinition> columnDefinitions) {
		columnDefinitions.forEach(columnDef -> {
			getContainer().addContainerProperty(columnDef.getPropertyName(), columnDef.getDataType(), null, true,
					columnDef.isSortable());
			Column column = getGrid().getColumn(columnDef.getPropertyName());
			column.setSortable(columnDef.isSortable());
			column.setHeaderCaption(columnDef.getCaption());
		});
	}
}
