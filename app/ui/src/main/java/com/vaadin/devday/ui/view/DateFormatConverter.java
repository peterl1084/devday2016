package com.vaadin.devday.ui.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

public class DateFormatConverter implements Converter<String, Date> {
	private static final long serialVersionUID = -6905145146333452274L;

	private SimpleDateFormat simpleDateFormat;

	public DateFormatConverter() {
		simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
	}

	@Override
	public Date convertToModel(String value, Class<? extends Date> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		return null;
	}

	@Override
	public String convertToPresentation(Date value, Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		return simpleDateFormat.format(value);
	}

	@Override
	public Class<Date> getModelType() {
		return Date.class;
	}

	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}
}
