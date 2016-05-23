package com.vaadin.devday.ui;

import com.vaadin.ui.Component;

public interface CanCastComponent {

	default Component asComponent() {
		return Component.class.cast(this);
	}
}
