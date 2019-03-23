package org.learning.hibernate.filtering.Listener;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ListenerTest {

	private EntityManagerFactory entityManagerFactory;
	
	@BeforeSuite
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("listeners");
	}
	
	@AfterSuite
	public void tearDown() {
		entityManagerFactory.close();
	}
	
	@Test
	public void testListener() {
		Item item = new Item("Ancient Relic", BigDecimal.valueOf(1000), Date.valueOf("2019-03-22"));
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(item);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
