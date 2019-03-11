package org.learning.hibernate.tableperclass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

public class TablePerClassTest extends TestCase{

	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("tableperclass");
	}
	
	@Override
	public void tearDown() {
		entityManagerFactory.close();
	}
	
	public void testBasicUsage() {
		BillingDetails billingDetails1 = new CreditCard("143", "Never", "Never");
		BillingDetails billingDetails2 = new BankAccount("1234", "NoName", "Infinite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(billingDetails1);
		entityManager.persist(billingDetails2);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public void testInheritence() {
		BillingDetails billingDetails = new CreditCard("143", "Never", "Never");
		Address address = new Address("Somalamma Temple Street", "533003", "Kakinada");
		User user = new User(address, billingDetails);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(billingDetails);
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		User retrievedUser = entityManager.find(User.class, user.getId());
		assertNotNull(retrievedUser.billingDetails);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
