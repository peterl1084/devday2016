package com.vaadin.devday.ui.navigation;

public class NavigationEvent {

	private final String viewName;

	public NavigationEvent(String viewName) {
		this.viewName = viewName;
	}

	public String getViewName() {
		return viewName;
	}
}
