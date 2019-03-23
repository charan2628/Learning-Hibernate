package org.learning.hibernate.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;

import org.learning.hibernate.lazyloading.Bid;
import org.learning.hibernate.lazyloading.Item;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LazyLoadingTest {

	private EntityManagerFactory entityManagerFactory;
	private Long ITEM_ID;
	
	@BeforeSuite
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("lazyloading");
		
		Item item = new Item("Ancient Relic", BigDecimal.valueOf(10000), Date.valueOf("2019-03-22"));
		Bid bid = new Bid(BigDecimal.valueOf(10500), Date.valueOf("2019-03-22"), item);
		Bid bid2 = new Bid(BigDecimal.valueOf(11000), Date.valueOf("2019-03-23"), item);
		Bid bid3 = new Bid(BigDecimal.valueOf(11500), Date.valueOf("2019-03-24"), item);
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(item);
		entityManager.persist(bid3);
		entityManager.persist(bid2);
		entityManager.persist(bid);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		ITEM_ID = item.getId();
	}
	
	@AfterSuite
	public void tearDown() {
		entityManagerFactory.close();
	}
	
	@Test
	public void testLazyLoading() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Item item = entityManager.find(Item.class, ITEM_ID);
		Set<Bid> bids = item.getBids();
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
		assertFalse(persistenceUtil.isLoaded(item, "bids"));
		assertTrue(Set.class.isAssignableFrom(bids.getClass()));
		assertNotEquals(bids.getClass(), HashSet.class);
		assertEquals(bids.getClass(), org.hibernate.collection.internal.PersistentSet.class);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	@Test
	public void testExtraLazyLoading() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Item item = entityManager.find(Item.class, ITEM_ID);
		PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
		assertFalse(persistenceUtil.isLoaded(item, "bids"));
		assertEquals(item.getBids().size(), 3);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
