package com.vaadin.devday.ui.mainmenu;

import com.vaadin.devday.ui.MainMenu.MainMenuItem;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

class MainMenuItemBean extends Button implements MainMenuItem {
	private static final long serialVersionUID = -3403596735393433619L;
	private final String viewName;

	public MainMenuItemBean(String caption, Resource icon, String viewName, Button.ClickListener clickListener) {
		super(caption, icon);
		this.viewName = viewName;
		addClickListener(clickListener);

		setPrimaryStyleName(ValoTheme.MENU_ITEM);

		setWidth(100, Unit.PERCENTAGE);
	}

	public String getViewName() {
		return viewName;
	}

	public void setSelected(boolean selected) {
		if (selected) {
			addStyleName("selected");
		} else {
			removeStyleName("selected");
		}
	}
}
