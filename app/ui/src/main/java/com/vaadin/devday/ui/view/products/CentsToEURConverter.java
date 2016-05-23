package com.vaadin.devday.ui.view.products;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

public class CentsToEURConverter implements Converter<String, Long> {
	private static final long serialVersionUID = -2827921184386406343L;

	@Override
	public Long convertToModel(String value, Class<? extends Long> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		if (value == null || value.isEmpty()) {
			return null;
		}

		value = value.replace(',', '.');
		value = value.substring(0, value.length() - 1).trim();
		BigDecimal valueOf = BigDecimal.valueOf(Double.parseDouble(value));
		return valueOf.multiply(BigDecimal.valueOf(100)).longValue();
	}

	@Override
	public String convertToPresentation(Long value, Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		if (value == null) {
			return null;
		}

		DecimalFormat format = (DecimalFormat) DecimalFormat.getInstance(locale);
		DecimalFormatSymbols symbols = format.getDecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		format.setDecimalFormatSymbols(symbols);

		format.setMaximumFractionDigits(2);
		format.setMinimumFractionDigits(2);

		BigDecimal cents = BigDecimal.valueOf(value);
		BigDecimal euros = cents.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

		return format.format(euros) + " €";
	}

	@Override
	public Class<Long> getModelType() {
		return Long.class;
	}

	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}
}
