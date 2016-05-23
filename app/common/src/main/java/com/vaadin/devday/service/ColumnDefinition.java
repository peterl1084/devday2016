package com.vaadin.devday.service;

public class ColumnDefinition {
	private String propertyName;
	private String caption;
	private Class<?> dataType;
	private String group;
	private boolean sortable;

	public ColumnDefinition(String propertyName, String caption, Class<?> dataType, String group, boolean sortable) {
		this.propertyName = propertyName;
		this.caption = caption;
		this.dataType = dataType;
		this.group = group;
		this.sortable = sortable;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public Class<?> getDataType() {
		return dataType;
	}

	public void setDataType(Class<?> dataType) {
		this.dataType = dataType;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
}
