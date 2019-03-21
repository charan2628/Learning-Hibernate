package org.learning.hibernate.model.test;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.learning.hibernate.model.Bid;
import org.learning.hibernate.model.Item;

import junit.framework.TestCase;

public class BidTest extends TestCase{

	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("org.learning.hibernate.chapter4");
	}
	
	@Override
	public void tearDown() {
		entityManagerFactory.close();
	}
	
	public void testItemsPersistence() {
		Item item = new Item("Ancient Sacred Relic");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(item);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Item> items = entityManager.createQuery("select i from Item i", Item.class).getResultList();
		assertEquals(1, items.size());
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	
	public void testBidsPersistence() {
		Item item = new Item("Ancient Super Duper Relic");
		Bid bid1 = new Bid(BigDecimal.valueOf(10000), item);
		Bid bid2 = new Bid(BigDecimal.valueOf(20000), item);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(item);
		entityManager.persist(bid1);
		entityManager.persist(bid2);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Bid> bids = entityManager.createQuery("select b from Bid b", Bid.class).getResultList();
		assertEquals(2, bids.size());
		assertNotNull(bids.get(0).getItem());
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
