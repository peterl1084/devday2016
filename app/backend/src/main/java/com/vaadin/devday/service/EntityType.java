package com.vaadin.devday.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import com.vaadin.devday.entity.AbstractEntity;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
@Qualifier
public @interface EntityType {

	Class<? extends AbstractEntity> value();
}
