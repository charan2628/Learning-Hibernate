package org.learning.hibenrate.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;

import org.learning.hibernate.concurrency.Item;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ConcurrencyTest {

	private EntityManagerFactory entityManagerFactory;
	
	@BeforeSuite
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("concurrency");
	}
	
	@AfterSuite
	public void tearDown() {
		entityManagerFactory.close();
	}
	
	@Test(priority = 1)
	public void testVersioning() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Item item = new Item("Ancient Relic");
		entityManager.getTransaction().begin();
		entityManager.persist(item);
		entityManager.getTransaction().commit();
		assertEquals(item.getVersion(), 0);
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		item = entityManager.find(Item.class, item.getId());
		item.setName("Sacred Relic");
		entityManager.flush();
		assertEquals(1, item.getVersion());
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	@Test(priority = 2)
	public void testManualVersionChecking() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Item> items = entityManager.createQuery("select i from Item i", Item.class)
				.setLockMode(LockModeType.OPTIMISTIC)
				.getResultList();
		entityManager.flush();
		removeItem(items.get(0).getId());
		Item item = entityManager.find(Item.class, items.get(0).getId());
		assertNotNull(item);
		entityManager.getTransaction().commit();
		entityManager.close();
		assertEquals(1, items.size());
	}
	
	
	public void addItem(String itemName) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Item item = new Item(itemName);
		entityManager.getTransaction().begin();
		entityManager.persist(item);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	
	public void updateItem(Long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Item item = entityManager.find(Item.class, id);
		item.setName("Super duper relic");
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public void removeItem(Long id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Item item = entityManager.find(Item.class, id);
		entityManager.remove(item);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
