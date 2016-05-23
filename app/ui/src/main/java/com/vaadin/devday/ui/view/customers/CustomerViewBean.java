package com.vaadin.devday.ui.view.customers;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.devday.service.DTOService;
import com.vaadin.devday.service.customer.CustomerEditDTO;
import com.vaadin.devday.service.customer.CustomerListingDTO;
import com.vaadin.devday.ui.MenuItem;
import com.vaadin.devday.ui.NavigationManager;
import com.vaadin.devday.ui.navigation.ViewChangeAllowedVerifier;
import com.vaadin.devday.ui.view.DoneWithPopupEvent;
import com.vaadin.devday.ui.view.DoneWithPopupEvent.Reason;
import com.vaadin.devday.ui.view.LazyDTOQuery;
import com.vaadin.devday.ui.view.LazyDataTable;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.VerticalLayout;

@CDIView("customers")
@MenuItem(name = "Customers", icon = FontAwesome.USERS, order = 1)
public class CustomerViewBean extends VerticalLayout implements View, ViewChangeAllowedVerifier {
	private static final long serialVersionUID = 4275003210340895767L;

	@EJB
	private DTOService dtoService;

	private LazyDataTable<CustomerListingDTO> customerTable;

	@Inject
	private NavigationManager navigationManager;

	public CustomerViewBean() {
		setSpacing(true);
	}

	@PostConstruct
	protected void initialize() {
		customerTable = new LazyDataTable<>(CustomerListingDTO.class,
				(definition) -> new LazyDTOQuery<>(CustomerListingDTO.class, dtoService, definition));
		customerTable.addItemClickListener(e -> onTableItemClicked(e));
		addComponent(customerTable);
		customerTable.setSizeFull();
		setExpandRatio(customerTable, 1);

		setSizeFull();
	}

	private void onTableItemClicked(ItemClickEvent event) {
		Object itemId = event.getItemId();
		CustomerEditDTO editDto = dtoService.getDtoById((Long) itemId, CustomerEditDTO.class);

		CustomerEditorPopup editorPopup = navigationManager.showInPopup(CustomerEditorPopup.class);
		editorPopup.showInEditor(editDto);
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	@Override
	public boolean isViewChangeAllowed() {
		return true;
	}

	protected void onDoneWithTheEditor(@Observes(notifyObserver = Reception.IF_EXISTS) DoneWithPopupEvent e) {
		if (Reason.SAVE.equals(e.getReason())) {
			customerTable.refresh();
		}
	}
}
