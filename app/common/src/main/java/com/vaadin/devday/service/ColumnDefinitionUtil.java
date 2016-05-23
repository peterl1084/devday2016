package com.vaadin.devday.service;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ColumnDefinitionUtil {

	public static List<ColumnDefinition> findColumnDefinitions(Class<? extends AbstractDTOWithIdentity> dtoType) {
		List<Field> tableColumnFields = Arrays.asList(dtoType.getDeclaredFields()).stream()
				.filter(field -> field.isAnnotationPresent(TableColumn.class)).collect(Collectors.toList());

		return tableColumnFields.stream().map(field -> {
			TableColumn columnAnnotation = field.getAnnotation(TableColumn.class);
			return new ColumnDefinition(field.getName(), columnAnnotation.name(), field.getType(),
					columnAnnotation.group(), columnAnnotation.sort());
		}).collect(Collectors.toList());
	}
}
