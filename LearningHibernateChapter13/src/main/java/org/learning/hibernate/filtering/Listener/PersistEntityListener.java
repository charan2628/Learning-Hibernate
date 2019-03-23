package org.learning.hibernate.filtering.Listener;

import javax.persistence.PostPersist;

public class PersistEntityListener {

	@PostPersist
	public void notifyAdmin(Object entityInstance) {
		System.out.println("NOTIFYING ADMIN " + entityInstance);
	}
}
