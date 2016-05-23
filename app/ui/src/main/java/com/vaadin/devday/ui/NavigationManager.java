package com.vaadin.devday.ui;

import com.vaadin.devday.ui.view.PopupView;
import com.vaadin.navigator.View;

public interface NavigationManager {

	View getCurrentView();

	<T extends PopupView> T showInPopup(Class<T> popupType);
}
