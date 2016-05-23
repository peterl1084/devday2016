package com.vaadin.devday.ui.view;

import com.vaadin.devday.ui.CanCastComponent;

public interface PopupView extends CanCastComponent {

	String getWindowCaption();

	void focusFirst();
}
