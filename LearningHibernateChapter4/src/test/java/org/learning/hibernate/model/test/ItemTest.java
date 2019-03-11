package org.learning.hibernate.model.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.learning.hibernate.model.Bid;
import org.learning.hibernate.model.Item;

import junit.framework.TestCase;

public class ItemTest extends TestCase{

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
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(new Item());
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Item> items = entityManager.createQuery("select i from Item i", Item.class).getResultList();
		assertEquals(1, items.size());
		assertEquals(1, items.get(0).getId().intValue());
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public void testAverageAmount() {
		Item item = new Item("Ancient Sacred Relic");
		Bid bid1 = new Bid(BigDecimal.valueOf(1500), item);
		Bid bid2 = new Bid(BigDecimal.valueOf(500), item);
		Bid bid3 = new Bid(BigDecimal.valueOf(3500), item);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(item);
		entityManager.persist(bid3);
		entityManager.persist(bid2);
		entityManager.persist(bid1);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Item> items = entityManager.createQuery("select i from Item i", Item.class).getResultList();
		System.out.println("Average Amount: " + items.get(0).getAverageBidAmount());
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
