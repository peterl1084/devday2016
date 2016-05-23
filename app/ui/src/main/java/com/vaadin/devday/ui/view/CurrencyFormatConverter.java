package com.vaadin.devday.ui.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

public class CurrencyFormatConverter implements Converter<String, BigDecimal> {
	private static final long serialVersionUID = 4205640187349548315L;

	@Override
	public BigDecimal convertToModel(String value, Class<? extends BigDecimal> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		return null;
	}

	@Override
	public String convertToPresentation(BigDecimal value, Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		float floatValue = value.setScale(2, RoundingMode.HALF_UP).floatValue();
		return floatValue + " €";
	}

	@Override
	public Class<BigDecimal> getModelType() {
		return BigDecimal.class;
	}

	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}
}
