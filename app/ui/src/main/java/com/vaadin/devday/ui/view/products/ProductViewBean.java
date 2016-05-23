package com.vaadin.devday.ui.view.products;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;

import com.vaadin.cdi.CDIView;
import com.vaadin.devday.service.DTOService;
import com.vaadin.devday.service.product.ProductEditDTO;
import com.vaadin.devday.service.product.ProductListingDTO;
import com.vaadin.devday.ui.MenuItem;
import com.vaadin.devday.ui.NavigationManager;
import com.vaadin.devday.ui.navigation.ViewChangeAllowedVerifier;
import com.vaadin.devday.ui.view.BeanDataTable;
import com.vaadin.devday.ui.view.DoneWithPopupEvent;
import com.vaadin.devday.ui.view.DoneWithPopupEvent.Reason;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@CDIView("products")
@MenuItem(name = "Products", icon = FontAwesome.GLASS, order = 1)
public class ProductViewBean extends VerticalLayout implements View, ViewChangeAllowedVerifier {
	private static final long serialVersionUID = 4275003210340895767L;

	@EJB
	private DTOService dtoService;

	private BeanDataTable<ProductListingDTO> productTable;

	@Inject
	private NavigationManager navigationManager;

	@PostConstruct
	protected void initialize() {
		productTable = new BeanDataTable<>(ProductListingDTO.class);
		productTable.addItemClickListener(e -> onTableItemClicked(e));
		productTable.setConverter(new CentsToEURConverter(), "priceCents");
		addComponent(productTable);
		productTable.setSizeFull();
		setExpandRatio(productTable, 1);

		setSizeFull();
	}

	private void onTableItemClicked(ItemClickEvent event) {
		Object itemId = event.getItemId();
		ProductEditDTO editDto = dtoService.getDtoById((Long) itemId, ProductEditDTO.class);

		ProductEditorPopup editorPopup = navigationManager.showInPopup(ProductEditorPopup.class);
		editorPopup.showInEditor(editDto);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		productTable.addStyleName("grid-loading");
		new Thread(() -> {
			List<ProductListingDTO> productData = dtoService.getAllDtos(ProductListingDTO.class);
			UI.getCurrent().access(() -> {
				productTable.setDataItems(productData);
				productTable.removeStyleName("grid-loading");
			});
		}).start();

	}

	@Override
	public boolean isViewChangeAllowed() {
		return true;
	}

	protected void onDoneWithTheEditor(@Observes(notifyObserver = Reception.IF_EXISTS) DoneWithPopupEvent e) {
		if (Reason.SAVE.equals(e.getReason())) {
			productTable.setDataItems(dtoService.getAllDtos(ProductListingDTO.class));
		}
	}
}
