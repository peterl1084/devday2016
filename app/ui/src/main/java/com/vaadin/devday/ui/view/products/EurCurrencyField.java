package com.vaadin.devday.ui.view.products;

import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.TextField;

/**
 * CurrencyField is a CustomField that is used to show Long value representing
 * EUR cents as EUR with two decimals.
 * 
 * @author Peter / Vaadin
 */
public class EurCurrencyField extends CustomField<Long> {

	private static final long serialVersionUID = -3805852579442541499L;

	private TextField textField;

	private CentsToEURConverter converter;

	public EurCurrencyField() {
		converter = new CentsToEURConverter();
		textField = new TextField();
		textField.setConverter(converter);
	}

	@Override
	public void setCaption(String caption) {
		textField.setCaption(caption);
	}

	@Override
	public String getCaption() {
		return textField.getCaption();
	}

	@Override
	protected void setInternalValue(Long newValue) {
		super.setInternalValue(newValue);

		textField.setConvertedValue(newValue);
	}

	@Override
	protected Long getInternalValue() {
		return (Long) textField.getConvertedValue();
	}

	@Override
	protected Component initContent() {
		return textField;
	}

	@Override
	public Class<Long> getType() {
		return Long.class;
	}
}
