package com.vaadin.devday.service;

import java.util.List;

import javax.ejb.Local;

import com.vaadin.devday.entity.AbstractEntity;

@Local
public interface EntityService {

	void storeEntity(AbstractEntity entity);

	<E extends AbstractEntity> List<E> getAll(Class<E> entityType);

	<E extends AbstractEntity> E getEntityById(long id, Class<E> entityType);

	<E extends AbstractEntity> List<E> getPagedEntities(Class<E> entityType, int startIndex, int items,
			Object[] sortPropertyIds, boolean[] sortStates);
}
