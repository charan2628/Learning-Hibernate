package org.learning.hibernate.interceptors;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class InterceptorTest {

	private EntityManagerFactory entityManagerFactory;
	
	@BeforeSuite
	public void setUp() {
		entityManagerFactory = Persistence.createEntityManagerFactory("interceptors");
	}
	
	@AfterSuite
	public void tearDown() {
		entityManagerFactory.close();
	}
	
	@Test
	public void testInterceptor() {
		Map<String, String> properties = new HashMap<>();
		properties.put(org.hibernate.jpa.AvailableSettings.SESSION_INTERCEPTOR, AuditLogInterceptor.class.getName());
		EntityManager entityManager = entityManagerFactory.createEntityManager(properties);
		Session session = entityManager.unwrap(Session.class);
		AuditLogInterceptor interceptor = (AuditLogInterceptor)((SessionImplementor) session).getInterceptor();
		interceptor.setCurrentSession(session);
		interceptor.setCurrentUserId(Long.valueOf(12));
		entityManager.getTransaction().begin();
		entityManager.persist(new Item("Ancient Relic", BigDecimal.valueOf(10000), Date.valueOf("2019-03-23")));
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
