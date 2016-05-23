package com.vaadin.devday.ui.navigation;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.cdi.NormalUIScoped;
import com.vaadin.devday.ui.NavigationManager;
import com.vaadin.devday.ui.UIInitializedEvent;
import com.vaadin.devday.ui.view.DoneWithPopupEvent;
import com.vaadin.devday.ui.view.PopupView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

@NormalUIScoped
class NavigationManagerBean extends Navigator implements NavigationManager {
	private static final long serialVersionUID = 6599898650948333853L;

	@Inject
	private ViewDisplay viewDisplay;

	@Inject
	private CDIViewProvider viewProvider;

	@Inject
	private ViewChangeListener viewChangeListener;

	private Map<PopupView, Window> popupMap;

	public NavigationManagerBean() {
		popupMap = new HashMap<>();
	}

	@Any
	@Inject
	private Instance<PopupView> popupViewInstantiator;

	@Inject
	private UriFragmentManager uriFragmentManager;

	@Inject
	private Event<NavigationEvent> navigationEventSource;

	protected void onUIInitialized(@Observes UIInitializedEvent e) {
		init(UI.getCurrent(), uriFragmentManager, viewDisplay);
		addProvider(viewProvider);
		addViewChangeListener(viewChangeListener);
	}

	public void navigateTo(String navigationState, boolean fireNavigationEvent) {
		if (fireNavigationEvent) {
			navigateTo(navigationState);
		} else {
			super.navigateTo(navigationState);
		}
	}

	@Override
	public void navigateTo(String navigationState) {
		super.navigateTo(navigationState);
		navigationEventSource.fire(new NavigationEvent(navigationState));
	}

	protected void onNavigationEvent(@Observes(notifyObserver = Reception.IF_EXISTS) NavigationEvent e) {
		navigateTo(e.getViewName(), false);
	}

	@Override
	public <T extends PopupView> T showInPopup(Class<T> popupType) {
		Instance<T> instanceSelection = popupViewInstantiator.select(popupType);
		PopupView popupContent = instanceSelection.get();

		Window window = new Window();
		window.setCaption(popupContent.getWindowCaption());
		window.center();
		window.setResizable(false);

		window.setContent(popupContent.asComponent());
		UI.getCurrent().addWindow(window);
		popupContent.focusFirst();

		popupMap.put(popupContent, window);

		return (T) popupContent;
	}

	protected void onDoneWithTheEditor(@Observes(notifyObserver = Reception.IF_EXISTS) DoneWithPopupEvent e) {
		Window window = popupMap.get(e.getPopup());
		if (window != null) {
			window.close();
			popupMap.remove(e.getPopup());
		}
	}
}
