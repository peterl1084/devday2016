package com.vaadin.devday.ui.navigation;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.vaadin.cdi.UIScoped;
import com.vaadin.devday.ui.NavigationManager;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;

@UIScoped
class ViewChangeListenerBean implements ViewChangeListener {
	private static final long serialVersionUID = 1913421359807383L;

	@Any
	@Inject
	private Instance<ViewChangeAllowedVerifier> verifiers;

	@Inject
	private NavigationManager navigationManager;

	@Inject
	private Event<AfterViewChangeEvent> afterViewChangeEventSource;

	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		View currentView = navigationManager.getCurrentView();
		if (currentView != null) {
			for (ViewChangeAllowedVerifier verifier : verifiers) {
				if (currentView.equals(verifier)) {
					if (!verifier.isViewChangeAllowed()) {
						System.out.println(verifier + " says NOO!");
						return false;
					} else {
						System.out.println(verifier + " says YYEEES!");
					}
				}
			}
		}

		return true;
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) {
		afterViewChangeEventSource.fire(new AfterViewChangeEvent());
	}

}
