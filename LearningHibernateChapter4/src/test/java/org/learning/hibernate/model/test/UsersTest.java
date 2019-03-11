package org.learning.hibernate.model.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.learning.hibernate.model.Address;
import org.learning.hibernate.model.User;

import junit.framework.TestCase;

public class UsersTest extends TestCase{

	private EntityManagerFactory entityManagerFactory;

	@Override
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("org.learning.hibernate.chapter4");
	}

	@Override
	public void tearDown() {
		entityManagerFactory.close();
	}
	
	public void testBasicUsage() {
		Address address = new Address("Somalamma Temple Street", "533003", "Kakinada");
		User user = new User(address);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
