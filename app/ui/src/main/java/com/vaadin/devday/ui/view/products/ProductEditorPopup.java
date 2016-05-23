package com.vaadin.devday.ui.view.products;

import com.vaadin.cdi.ViewScoped;
import com.vaadin.devday.service.product.ProductEditDTO;
import com.vaadin.devday.ui.view.AbstractPopupEditor;
import com.vaadin.ui.VerticalLayout;

@ViewScoped
public class ProductEditorPopup extends AbstractPopupEditor<ProductEditDTO> {
	private static final long serialVersionUID = -4532474543684849677L;

	public ProductEditorPopup() {
		super(new VerticalLayout(), ProductEditDTO.class);
		bindDesign(new ProductEditorDesign());
	}

	@Override
	public String getWindowCaption() {
		return "Edit product";
	}

	@Override
	public void focusFirst() {
	}
}
