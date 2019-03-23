package org.learning.hibernate.queries;

import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class PolymorphicQueriesTest {

	private EntityManagerFactory entityManagerFactory;
	
	@BeforeSuite
	public void setUp() {
		Item item = new Item("Ancient Relic", BigDecimal.valueOf(1000), new Date());
		Item item2 = new Item("Ancient Super Duper Relic", BigDecimal.valueOf(1500), new Date());
		Item item3 = new Item("Sacred Relic", BigDecimal.valueOf(60000), new Date());
		Bid bid = new Bid(BigDecimal.valueOf(1000), new Date(), item);
		Bid bid2 = new Bid(BigDecimal.valueOf(1600), new Date(), item);
		Bid bid3 = new Bid(BigDecimal.valueOf(1200), new Date(), item2);
		Bid bid4 = new Bid(BigDecimal.valueOf(2100), new Date(), item2);
		Bid bid5 = new Bid(BigDecimal.valueOf(1300), new Date(), item3);
		Bid bid6 = new Bid(BigDecimal.valueOf(2400), new Date(), item3);
		entityManagerFactory = Persistence.createEntityManagerFactory("polymorphicqueries");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		CreditCard creditCard = new CreditCard("12345", "03", "2021");
		BankAccount bankAccount = new BankAccount("Savings", "HDFC", "Desire");
		entityManager.persist(creditCard);
		entityManager.persist(bankAccount);
		entityManager.persist(item3);
		entityManager.persist(item);
		entityManager.persist(item2);
		entityManager.persist(bid6);
		entityManager.persist(bid5);
		entityManager.persist(bid4);
		entityManager.persist(bid3);
		entityManager.persist(bid2);
		entityManager.persist(bid);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@AfterSuite
	public void tearDown() {
		entityManagerFactory.close();
	}
	
	@Test
	public void polymorphicTestUsingQueries() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<CreditCard> creditCards = entityManager.createQuery("select cc from CreditCard cc", CreditCard.class).getResultList();
		assertEquals(creditCards.size(), 1);
		List<BillingDetails> billingDetails = entityManager.createQuery("select bd from BillingDetails bd", BillingDetails.class).getResultList();
		assertEquals(billingDetails.size(), 2);
		List<BankAccount> bankAccounts = entityManager.createQuery("select ba from BankAccount ba", BankAccount.class).getResultList();
		assertEquals(bankAccounts.size(), 1);
		
		billingDetails = entityManager.createQuery("select bd from BillingDetails bd where type(bd) = CreditCard", BillingDetails.class)
				.getResultList();
		assertEquals(billingDetails.size(), 1);
		
		TypedQuery<BillingDetails> query = entityManager.createQuery("select bd from BillingDetails bd where type(bd) in :types", BillingDetails.class);
		query.setParameter("types", Arrays.asList(CreditCard.class));
		billingDetails = query.getResultList();
		assertEquals(billingDetails.size(), 1);

		entityManager.close();
	}
	
	@Test
	public void polymorphicTestUsingCriteria() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CreditCard> criteria = criteriaBuilder.createQuery(CreditCard.class);
		criteria.select(criteria.from(CreditCard.class));
		TypedQuery<CreditCard> typedQuery = entityManager.createQuery(criteria);
		List<CreditCard> creditCards = typedQuery.getResultList();
		assertEquals(creditCards.size(), 1);
		
		CriteriaQuery<BankAccount> criteria2 = criteriaBuilder.createQuery(BankAccount.class);
		criteria2.select(criteria2.from(BankAccount.class));
		List<BankAccount> bankAccounts = entityManager.createQuery(criteria2).getResultList();
		assertEquals(bankAccounts.size(), 1);
		
		CriteriaQuery<BillingDetails> criteria3 = criteriaBuilder.createQuery(BillingDetails.class);
		criteria3.select(criteria3.from(BillingDetails.class));
		List<BillingDetails> billingDetails = entityManager.createQuery(criteria3).getResultList();
		assertEquals(billingDetails.size(), 2);
		
		criteria3 = criteriaBuilder.createQuery(BillingDetails.class);
		Root<BillingDetails> bd = criteria3.from(BillingDetails.class);
		criteria3.select(bd).where(criteriaBuilder.equal(bd.type(), CreditCard.class));
		billingDetails = entityManager.createQuery(criteria3).getResultList();
		assertEquals(billingDetails.size(), 1);
		
		criteria3 = criteriaBuilder.createQuery(BillingDetails.class);
		bd = criteria3.from(BillingDetails.class);
		criteria3.select(bd).where(bd.type().in(criteriaBuilder.parameter(List.class, "types")));
		billingDetails = entityManager.createQuery(criteria3).setParameter("types", Arrays.asList(CreditCard.class, BankAccount.class))
				.getResultList();
		assertEquals(billingDetails.size(), 2);
		
		entityManager.close();
	}

	@Test
	public void polymorphicTestUsingQueries2() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<Item> query = entityManager.createQuery("select i from Item i where i.name like '%Super%'", Item.class);
		List<Item> items = query.getResultList();
		assertEquals(items.size(), 1);
		entityManager.close();
	}
	
	@Test
	public void polymorphicTestUsingCriteria2() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Item> query = cb.createQuery(Item.class);
		Root<Item> i = query.from(Item.class);
		query.select(i).where(cb.like(i.<String>get("name"), "%Super%"));
		List<Item> items = entityManager.createQuery(query).getResultList();
		assertEquals(items.size(), 1);
	}

	@Test
	public void polymorphicTestUsingQueries3() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Bid> bids = entityManager.createQuery("select b from Bid b where b.amount between 1000 and 2000", Bid.class).getResultList();
		assertEquals(bids.size(), 4);
		entityManager.close();
	}
	
	@Test
	public void polymorphicTestUsingCriteria3() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Bid> criteria = cb.createQuery(Bid.class);
		Root<Bid> b = criteria.from(Bid.class);
		criteria.select(b).where(cb.between(b.<BigDecimal>get("amount"), BigDecimal.valueOf(1000), BigDecimal.valueOf(2000)));
		List<Bid> bids = entityManager.createQuery(criteria).getResultList();
		assertEquals(bids.size(), 4);
		criteria = cb.createQuery(Bid.class);
		b = criteria.from(Bid.class);
		criteria.select(b).where(cb.gt(b.<BigDecimal>get("amount"), BigDecimal.valueOf(2000)));
		bids = entityManager.createQuery(criteria).getResultList();
		assertEquals(bids.size(), 2);
		entityManager.close();
	}
	
	
}
