package org.learning.hibernate.collections.setofstrings;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

public class SetOfStringsTest extends TestCase{

	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("setofstrings");
	}
	
	@Override
	public void tearDown() {
		entityManagerFactory.close();
	}
	
	public void testBasicUsage() {
		Item item = new Item("Ancient Relic");
		Image image = new Image(item, "relic1.jpg");
		Image image2 = new Image(item, "relic2.jpg");
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(item);
		entityManager.persist(image);
		entityManager.persist(image2);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		item = entityManager.find(Item.class, item.getId());
		assertTrue(item.getImages().contains("relic1.jpg"));
		assertTrue(item.getImages().contains("relic2.jpg"));
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
