package org.learning.hibernate.managingdata.test;

import static org.junit.Assert.assertNotEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import org.learning.hibernate.managingdata.Item;

import junit.framework.TestCase;

public class ManagingDataTest extends TestCase{
	
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("managingdata");
	}
	
	@Override
	public void tearDown() {
		entityManagerFactory.close();
	}
	
	public void testCanonicalUnitOfWork() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Item item = new Item("Ancient Relic");
		entityManager.persist(item);
		item = entityManager.find(Item.class, Long.valueOf(2));
		entityManager.close();
		assertNull(item);
	}
	
	public void testGettingReference() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Item item = new Item("Ancient Relic");
		entityManager.persist(item);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		item = entityManager.getReference(Item.class, item.getId());
		PersistenceUtil persistenceUtil = entityManagerFactory.getPersistenceUnitUtil();
		assertFalse(persistenceUtil.isLoaded(item));
		assertEquals(item.getName(), "Ancient Relic");
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public void testRefreshingData() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Item item = new Item("Ancient Relic");
		entityManager.persist(item);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		item = entityManager.find(Item.class, item.getId());
		item.setName("Sacred Relic");
		String oldName = item.getName();
		entityManager.refresh(item);
		assertNotEquals(oldName, item.getName());
	}
	
	

}
