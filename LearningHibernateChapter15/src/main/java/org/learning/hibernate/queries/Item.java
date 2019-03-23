package org.learning.hibernate.queries;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Item {

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	private Long id;
	private String name;
	private BigDecimal initialPrice;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date auctionEnd;

	public Item() {
		super();
	}

	public Item(String name, BigDecimal initialPrice, Date auctionEnd) {
		super();
		this.name = name;
		this.initialPrice = initialPrice;
		this.auctionEnd = auctionEnd;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(BigDecimal initialPrice) {
		this.initialPrice = initialPrice;
	}

	public Date getAuctionEnd() {
		return auctionEnd;
	}

	public void setAuctionEnd(Date auctionEnd) {
		this.auctionEnd = auctionEnd;
	}
	
}
