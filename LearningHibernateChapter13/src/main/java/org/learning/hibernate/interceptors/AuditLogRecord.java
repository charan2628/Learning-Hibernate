package org.learning.hibernate.interceptors;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class AuditLogRecord {

	private Long id;
	private String message;
	private Long entityId;
	private Class entityClass;
	private Long userId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn = new Date();
	
	public AuditLogRecord() {
		super();
	}

	public AuditLogRecord(String message, Auditable entityInstance, Long userId) {
		super();
		this.message = message;
		this.entityId = entityInstance.getId();
		this.entityClass = entityInstance.getClass();
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Class getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class entityClass) {
		this.entityClass = entityClass;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
}
