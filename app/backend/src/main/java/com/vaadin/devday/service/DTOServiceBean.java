package com.vaadin.devday.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.vaadin.devday.entity.AbstractEntity;

@Stateless
public class DTOServiceBean implements DTOService, EntityService {

	@PersistenceContext(unitName = "devday")
	private EntityManager entityManager;

	@Any
	@Inject
	private Instance<EntityToDTOConverter> entityToDTOConverterInstantiator;

	@Any
	@Inject
	private Instance<DTOToEntityConverter> dtoToEntityConverterInstantiator;

	@Override
	public <DTO extends AbstractDTOWithIdentity> DTO getDtoById(long itemId, Class<DTO> dtoType) {
		EntityToDTOConverter converter = findEntityToDTOConverter(dtoType);

		return (DTO) converter.convertToDTO(getEntityById(itemId, converter.getEntityType()));
	}

	@Override
	public <DTO extends AbstractDTOWithIdentity> List<DTO> getAllDtos(Class<DTO> dtoType) {
		EntityToDTOConverter converter = findEntityToDTOConverter(dtoType);

		// try {
		// Thread.sleep(10000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		return (List<DTO>) getAll(converter.getEntityType()).stream().map(entity -> converter.convertToDTO(entity))
				.collect(Collectors.toList());
	}

	@Override
	public <DTO extends AbstractDTOWithIdentity> List<DTO> getPagedDtos(Class<DTO> dtoType, int startIndex, int items,
			Object[] sortPropertyIds, boolean[] sortStates) {
		EntityToDTOConverter converter = findEntityToDTOConverter(dtoType);

		return (List<DTO>) getPagedEntities(converter.getEntityType(), startIndex, items, sortPropertyIds, sortStates)
				.stream().map(entity -> converter.convertToDTO(entity)).collect(Collectors.toList());
	}

	protected EntityToDTOConverter findEntityToDTOConverter(Class<? extends AbstractDTOWithIdentity> dtoType) {
		Instance<EntityToDTOConverter> converterSelection = entityToDTOConverterInstantiator
				.select(new DTOTypeAnnotationLiteral(dtoType));
		if (converterSelection.isAmbiguous()) {
			throw new RuntimeException("Ambiguous converters available for DTO type " + dtoType.getSimpleName());
		}
		if (converterSelection.isUnsatisfied()) {
			throw new RuntimeException("No converter available for DTO type " + dtoType.getSimpleName());
		}

		return converterSelection.get();
	}

	protected DTOToEntityConverter findDTOToEntityConverter(Class<? extends AbstractDTOWithIdentity> dtoType) {
		Instance<DTOToEntityConverter> converterSelection = dtoToEntityConverterInstantiator
				.select(new DTOTypeAnnotationLiteral(dtoType));
		if (converterSelection.isAmbiguous()) {
			throw new RuntimeException("Ambiguous converters available for DTO type " + dtoType.getSimpleName());
		}
		if (converterSelection.isUnsatisfied()) {
			throw new RuntimeException("No converter available for DTO type " + dtoType.getSimpleName());
		}

		return converterSelection.get();
	}

	@Override
	public <DTO extends AbstractDTOWithIdentity> long getCount(Class<DTO> dtoType) {
		EntityToDTOConverter converter = findEntityToDTOConverter(dtoType);
		String entityName = converter.getEntityType().getSimpleName();

		return entityManager.createQuery("SELECT COUNT(e.id) FROM " + entityName + " e", Long.class).getSingleResult();
	}

	@Override
	public void storeEntity(AbstractEntity entity) {
		entityManager.merge(entity);
	}

	@Override
	public <DTO extends AbstractDTOWithIdentity> void storeDto(DTO dto) {
		DTOToEntityConverter DTOToEntityConverter = findDTOToEntityConverter(dto.getClass());
		AbstractEntity entity = DTOToEntityConverter.convertToEntity(dto);
		storeEntity(entity);
	}

	@Override
	public <E extends AbstractEntity> E getEntityById(long id, Class<E> entityType) {
		TypedQuery<AbstractEntity> query = entityManager.createQuery(
				"SELECT e FROM " + entityType.getSimpleName() + " e WHERE e.id = :id", AbstractEntity.class);

		try {
			return (E) query.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			throw new RuntimeException("Could not find entity of type " + entityType.getSimpleName() + " with id " + id,
					e);
		}
	}

	@Override
	public <E extends AbstractEntity> List<E> getAll(Class<E> entityType) {
		return (List<E>) entityManager
				.createQuery("SELECT e FROM " + entityType.getSimpleName() + " e", AbstractEntity.class)
				.getResultList();
	}

	@Override
	public <E extends AbstractEntity> List<E> getPagedEntities(Class<E> entityType, int startIndex, int items,
			Object[] sortPropertyIds, boolean[] sortStates) {
		List<Object> orderByProperties = Arrays.asList(sortPropertyIds);

		Iterator<Object> orderByIterator = orderByProperties.iterator();
		StringBuilder orderByStringBuilder = new StringBuilder();

		int orderByIndex = 0;
		if (sortPropertyIds.length > 0) {
			orderByStringBuilder.append("ORDER BY ");
		}
		while (orderByIterator.hasNext()) {
			orderByStringBuilder.append("e.");
			orderByStringBuilder.append(orderByIterator.next());
			orderByStringBuilder.append(" ");
			orderByStringBuilder.append(sortStates[orderByIndex] ? "ASC" : "DESC");
			if (orderByIterator.hasNext()) {
				orderByStringBuilder.append(",");
			}
			orderByIndex++;
		}

		TypedQuery<AbstractEntity> query = entityManager.createQuery(
				"SELECT e FROM " + entityType.getSimpleName() + " e " + orderByStringBuilder.toString(),
				AbstractEntity.class);
		return (List<E>) query.setFirstResult(startIndex).setMaxResults(items).getResultList();
	}
}
