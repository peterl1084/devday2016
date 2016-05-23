package com.vaadin.devday.ui.navigation;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.vaadin.cdi.UIScoped;
import com.vaadin.navigator.Navigator.UriFragmentManager;
import com.vaadin.server.Page;
import com.vaadin.server.Page.UriFragmentChangedEvent;

@UIScoped
public class NavigationUriFragmentManager extends UriFragmentManager {
	private static final long serialVersionUID = -3661607002968436155L;

	@Inject
	private Event<NavigationEvent> eventSource;

	public NavigationUriFragmentManager() {
		super(Page.getCurrent());
	}

	@Override
	public void uriFragmentChanged(UriFragmentChangedEvent event) {
		eventSource.fire(new NavigationEvent(getState()));
	}
}
