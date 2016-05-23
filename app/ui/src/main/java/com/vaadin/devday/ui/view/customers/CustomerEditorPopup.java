package com.vaadin.devday.ui.view.customers;

import javax.inject.Inject;

import com.vaadin.cdi.ViewScoped;
import com.vaadin.devday.service.customer.CustomerEditDTO;
import com.vaadin.devday.ui.NavigationManager;
import com.vaadin.devday.ui.view.AbstractPopupEditor;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

@ViewScoped
public class CustomerEditorPopup extends AbstractPopupEditor<CustomerEditDTO> {
	private static final long serialVersionUID = -4532474543684849677L;
	private TextField firstField;

	@Inject
	private NavigationManager navigationManager;

	public CustomerEditorPopup() {
		super(CustomerEditDTO.class);

		Button showInvoices = new Button("Show invoices", e -> onShowInvoicesClicked());
		showInvoices.setIcon(FontAwesome.EUR);
		showInvoices.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		showInvoices.addStyleName(ValoTheme.BUTTON_TINY);
		getMainLayout().addComponentAsFirst(showInvoices);
		getMainLayout().setComponentAlignment(showInvoices, Alignment.TOP_RIGHT);

		firstField = addTextField("First name", "firstName");
		addTextField("Last name", "lastName");
		addDateField("Birth date", "birthDate");
	}

	private void onShowInvoicesClicked() {
		CustomerInvoicesPopup invoicePopup = navigationManager.showInPopup(CustomerInvoicesPopup.class);
		invoicePopup.setCustomerId(getBean().getId());
	}

	@Override
	public String getWindowCaption() {
		return "Edit customer";
	}

	@Override
	public void focusFirst() {
		firstField.focus();
	}
}
