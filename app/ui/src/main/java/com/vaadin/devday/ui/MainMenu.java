package com.vaadin.devday.ui;

import com.vaadin.server.Resource;

/**
 * MainMenu acts as bean interface for components that appear as application
 * main menu. Which main menu implementation will be shown is decided on runtime
 * by dependency injection.
 * 
 * @author Peter / Vaadin
 *
 */
public interface MainMenu extends CanCastComponent {

	/**
	 * Adds new menu item with given caption and icon to the menu.
	 * 
	 * @param caption
	 * @param icon
	 * @param navigationResource
	 * @return MainMenuItem representing a handle for the added item
	 */
	MainMenuItem addMenuItem(String caption, Resource icon, String navigationResource);

	public interface MainMenuItem {
		String getCaption();

		Resource getIcon();
	}
}
