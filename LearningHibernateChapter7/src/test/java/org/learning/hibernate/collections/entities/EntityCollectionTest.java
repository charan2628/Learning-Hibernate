package org.learning.hibernate.collections.entities;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

public class EntityCollectionTest extends TestCase{

	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("entityCollections");
	}
	
	@Override
	public void tearDown() {
		entityManagerFactory.close();
	}
	
	public void testBasicUsage() {
		Item item = new Item("Ancient Relic");
		User user = new User("charan", "Sai Charan", "Gudala");
		Bid bid = new Bid(BigDecimal.valueOf(123), Date.valueOf("2019-01-12"), user, item);
		Bid bid2 = new Bid(BigDecimal.valueOf(124), Date.valueOf("2019-01-12"), user, item);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.persist(item);
		item.getBids().add(bid);
		item.getBids().add(bid2);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		item = entityManager.find(Item.class, item.getId());
		entityManager.remove(item);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		user = entityManager.find(User.class, user.getId());
		assertEquals(user.getBids().size(), 0);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
}
