package com.vaadin.devday.ui;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

import com.vaadin.server.FontAwesome;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Qualifier
public @interface MenuItem {

	@Nonbinding
	String name();

	@Nonbinding
	FontAwesome icon();

	@Nonbinding
	int order();
}
