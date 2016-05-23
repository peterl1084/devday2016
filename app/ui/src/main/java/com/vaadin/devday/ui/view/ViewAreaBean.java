package com.vaadin.devday.ui.view;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.vaadin.cdi.NormalUIScoped;
import com.vaadin.devday.ui.MainMenu;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 * ViewAreaBean is the default implementation of ViewArea that is used to
 * position the fundamental UI elements on screen. ViewAreaBean supports dynamic
 * main menu assignment as well as allows easier handling of popup windows.
 * 
 * @author Peter / Vaadin
 */

@NormalUIScoped
class ViewAreaBean extends HorizontalLayout implements ViewDisplay {
	private static final long serialVersionUID = 8912367165667036243L;

	private MainMenu mainMenu;
	private CssLayout contentArea;

	@Inject
	private Instance<MainMenu> mainMenuInstantiator;

	public ViewAreaBean() {
		setSizeFull();

		contentArea = new CssLayout();
		contentArea.setPrimaryStyleName("valo-content");
		contentArea.addStyleName("v-scrollable");
		contentArea.setSizeFull();

		addComponent(contentArea);
		setExpandRatio(contentArea, 1);
	}

	@PostConstruct
	protected void initialize() {
		if (mainMenuInstantiator.isAmbiguous()) {
			throw new RuntimeException("Ambiguous main menu implementations available, please refine your deployment");
		}

		if (!mainMenuInstantiator.isUnsatisfied()) {
			mainMenu = mainMenuInstantiator.get();
			addComponentAsFirst(mainMenu.asComponent());
		}
	}

	@Override
	public void showView(View view) {
		contentArea.removeAllComponents();
		contentArea.addComponent(Component.class.cast(view));
	}
}
