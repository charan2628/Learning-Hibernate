package org.learning.hibernate.managingdata;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bid {

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	protected Long id;
	protected BigDecimal amount;
	protected Date createdOn;
	
	public Bid() {
		super();
	}
	
	public Bid(BigDecimal amount, Date createdOn) {
		super();
		this.amount = amount;
		this.createdOn = createdOn;
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
}
