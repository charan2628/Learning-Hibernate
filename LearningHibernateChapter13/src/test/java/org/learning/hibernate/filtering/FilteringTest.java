package org.learning.hibernate.filtering;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class FilteringTest {

	private EntityManagerFactory entityManagerFactory;
	private Long ITEM_ID;
	
	@BeforeSuite
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("filtering");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Item item = new Item("Ancient Relic", BigDecimal.valueOf(500), Date.valueOf("2019-03-22"));
		Bid bid = new Bid(BigDecimal.valueOf(600), Date.valueOf("2019-03-22"), item);
		Bid bid2 = new Bid(BigDecimal.valueOf(700), Date.valueOf("2019-03-23"), item);
		item.getBids().add(bid2);
		item.getBids().add(bid);
		entityManager.persist(item);
		entityManager.getTransaction().commit();
		entityManager.close();
		ITEM_ID = item.getId();
	}
	
	@AfterSuite
	public void tearDown() {
		entityManagerFactory.close();
	}
	
	@Test
	public void testMergeAndDetach() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Item item = entityManager.find(Item.class, ITEM_ID);
		assertEquals(item.getBids().size(), 2);
		entityManager.detach(item);
		assertFalse(entityManager.contains(item));
		assertFalse(entityManager.contains(item.getBids().iterator().next()));
		item.setName("Ancient Sacred Relic");
		item.getBids().add(new Bid(BigDecimal.valueOf(750), Date.valueOf("2019-03-24"), item));
		entityManager.merge(item);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		item = entityManager.find(Item.class, ITEM_ID);
		assertEquals(item.getName(), "Ancient Sacred Relic");
		assertEquals(item.getBids().size(), 3);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
