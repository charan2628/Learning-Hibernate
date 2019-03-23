package org.learning.hibernate.interceptors;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;
import org.hibernate.type.Type;

public class AuditLogInterceptor extends EmptyInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -994612592077049204L;
	protected Session currentSession;
	protected Long currentUserId;
	protected Set<Auditable> inserts = new HashSet<>();
	protected Set<Auditable> updates = new HashSet<>();
	
	public void setCurrentSession(Session session) {
		this.currentSession = session;
	}
	
	public void setCurrentUserId(Long currentUserId) {
		this.currentUserId = currentUserId;
	}
	
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyName, Type[] types) 
					throws CallbackException{
		if (entity instanceof Auditable)
			inserts.add((Auditable)entity);
		return false;
	}
	
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) throws CallbackException{
		if(entity instanceof Auditable)
			updates.add((Auditable)entity);
		return false;
	}
	
	public void postFlush(Iterator iterator) throws CallbackException{
		Session tempSession = currentSession.sessionWithOptions().transactionContext().connection().openSession();
		try {
			for(Auditable entity: inserts) {
				tempSession.persist(new AuditLogRecord("insert", entity, currentUserId));
			}
			for(Auditable entity: updates) {
				tempSession.persist(new AuditLogRecord("update", entity, currentUserId));
			}
			tempSession.flush();
		} finally {
			tempSession.close();
			inserts.clear();
			updates.clear();
		}
	}
}
