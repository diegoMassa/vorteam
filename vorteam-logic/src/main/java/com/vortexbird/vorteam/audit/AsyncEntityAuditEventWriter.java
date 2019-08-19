package com.vortexbird.vorteam.audit;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.vorteam.domain.AbstractAuditingEntity;
import com.vortexbird.vorteam.domain.ZcodeEntityAuditEvent;
import com.vortexbird.vorteam.repository.ZcodeEntityAuditEventRepository;

/**
 * @author dgomez Async Entity Audit Event writer This is invoked by Hibernate
 *         entity listeners to write audit event for entitities
 */
@Component
public class AsyncEntityAuditEventWriter {

	private final Logger log = LoggerFactory.getLogger(AsyncEntityAuditEventWriter.class);

	@Autowired
	private ZcodeEntityAuditEventRepository auditingEntityRepository;

	private final HibernateAwareObjectMapper objectMapper = new HibernateAwareObjectMapper(); // Jackson object mapper
	// configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

	/**
	 * Writes audit events to DB asynchronously in a new thread
	 */
	@Async
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void writeAuditEvent(Object target, EntityAuditAction action) {
		log.debug("-------------- Post {} audit  --------------", action.value());
		try {
			ZcodeEntityAuditEvent auditedEntity = prepareAuditEntity(target, action);
			if (auditedEntity != null) {
				auditingEntityRepository.save(auditedEntity);
			}
		} catch (Exception e) {
			log.error("Exception while persisting audit entity for {} error: {}", target, e);
		}
	}

	/**
	 * Method to prepare auditing entity
	 *
	 * @param entity
	 * @param action
	 * @return
	 */
	private ZcodeEntityAuditEvent prepareAuditEntity(final Object entity, EntityAuditAction action) {
		ZcodeEntityAuditEvent auditedEntity = new ZcodeEntityAuditEvent();
		Class<?> entityClass = entity.getClass(); // Retrieve entity class with reflection
		auditedEntity.setAction(action.value());
		auditedEntity.setEntityType(entityClass.getName());
		String entityId = "";
		String entityData;
		log.trace("Getting Entity Id and Content");
		try {
			Object id = getId(entityClass, entity);
			entityId = id.toString();
			entityData = objectMapper.writeValueAsString(entity);
		} catch (IllegalArgumentException | SecurityException | NoSuchMethodException | IOException
				| IllegalAccessException | InvocationTargetException e) {
			log.error("Exception while getting entity ID and content {}", e);
			// returning null as we dont want to raise an application exception here
			return null;
		}
		auditedEntity.setEntityId(entityId);
		auditedEntity.setEntityValue(entityData);
		final AbstractAuditingEntity abstractAuditEntity = (AbstractAuditingEntity) entity;

		if (EntityAuditAction.CREATE.equals(action)) {
			auditedEntity.setModifiedBy(abstractAuditEntity.getUsuarioCreador());
			auditedEntity.setModifiedDate(new Date());
			auditedEntity.setCommitVersion(1);
		} else if (EntityAuditAction.UPDATE.equals(action)) {
			auditedEntity.setModifiedBy(abstractAuditEntity.getUsuarioModificador());
			auditedEntity.setModifiedDate(new Date());
			calculateVersion(auditedEntity);
		} else if (EntityAuditAction.DELETE.equals(action)) {
			auditedEntity.setModifiedBy(abstractAuditEntity.getUsuarioModificador());
			auditedEntity.setModifiedDate(new Date());
			calculateVersion(auditedEntity);
		}
		log.trace("Audit Entity --> {} ", auditedEntity.toString());
		return auditedEntity;
	}

	private Object getId(@SuppressWarnings("rawtypes") Class entityClass, Object entity) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object result = null;
		for (Method method : entityClass.getDeclaredMethods()) {
			if (method.getAnnotation(Id.class) != null) {
				log.debug("@id");
				log.debug("primary key:" + method.getName());
				log.debug("Value key:" + method.invoke(entity));
				result = method.invoke(entity);
				return result;
			}
		}
		if (result == null) {
			for (Field field : entityClass.getDeclaredFields()) {
				if (field.getAnnotation(Id.class) != null) {
					log.debug("@id");
					log.debug("primary key:" + field.getName());
					field.setAccessible(true);
					log.debug("Value key:" + field.get(entity));
					result = field.get(entity);
					return result;
				}
			}
		}
		return result;

	}

	private void calculateVersion(ZcodeEntityAuditEvent auditedEntity) {
		try {

			log.trace("Version calculation. for update/remove");
			Integer lastCommitVersion = auditingEntityRepository
					.countByEntityTypeAndEntityId(auditedEntity.getEntityType(), auditedEntity.getEntityId());
			log.trace("Last commit version of entity => {}", lastCommitVersion);
			if (lastCommitVersion != null && lastCommitVersion != 0) {
				log.trace("Present. Adding version..");
				auditedEntity.setCommitVersion(lastCommitVersion + 1);
			} else {
				log.trace("No entities.. Adding new version 1");
				auditedEntity.setCommitVersion(1);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
}
