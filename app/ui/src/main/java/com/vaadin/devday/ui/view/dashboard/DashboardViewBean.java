package com.vaadin.devday.ui.view.dashboard;

import com.vaadin.cdi.CDIView;
import com.vaadin.devday.ui.MenuItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@CDIView("")
@MenuItem(name = "Devday 2016", icon = FontAwesome.TACHOMETER, order = 0)
public class DashboardViewBean extends VerticalLayout implements View {
	private static final long serialVersionUID = -5989712795733589211L;

	public DashboardViewBean() {
		setMargin(true);
		addComponent(new Label("Devday 2016"));
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}
}
