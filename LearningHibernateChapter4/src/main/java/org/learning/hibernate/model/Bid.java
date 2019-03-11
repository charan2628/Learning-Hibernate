package org.learning.hibernate.model;

import java.math.BigDecimal;

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
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ITEM_ID_NO")
	protected Item item;
	
	public Bid() {
		super();
	}

	public Bid(BigDecimal amount, Item item) {
		super();
		this.amount = amount;
		this.item = item;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
}
