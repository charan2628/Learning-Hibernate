package org.learning.hibernate.tests;

import static org.testng.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.hibernate.proxy.HibernateProxyHelper;
import org.learning.hibernate.entityproxies.Item;
import org.learning.hibernate.entityproxies.User;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ProxyTest {

	private EntityManagerFactory entityManagerFactory;
	
	@BeforeSuite
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("entityproxy");
	}
	
	@AfterSuite
	public void tearDown() {
		entityManagerFactory.close();
	}
	
	@Test
	public void testReference() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		User user = new User("charan", "Sai Charan", "Gudala");
		Item item = new Item("Ancient Relic", user);
		entityManager.persist(user);
		entityManager.persist(item);
		entityManager.flush();
		entityManager.clear();
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		item = entityManager.getReference(Item.class, item.getId());
		assertNotEquals(item.getClass(), Item.class);
		assertEquals(HibernateProxyHelper.getClassWithoutInitializingProxy(item), Item.class);
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
		assertFalse(persistenceUtil.isLoaded(item));
		assertFalse(persistenceUtil.isLoaded(item, "seller"));
		item.getSeller();
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
