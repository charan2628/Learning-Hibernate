package org.learning.hibernate.collections.entities;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Bid {

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	protected Long id;
	protected BigDecimal amount;
	protected Date createdOn;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	protected User owner;
	
	@ManyToOne 
	protected Item item;
	
	public Bid() {
		super();
	}
	
	public Bid(BigDecimal amount, Date createdOn, User owner, Item item) {
		super();
		this.amount = amount;
		this.createdOn = createdOn;
		this.owner = owner;
		this.item = item;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Item getItem() {
		return item;
	}
	
	
}
