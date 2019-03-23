package org.learning.hibernate.interceptors;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Item implements Auditable{

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	private Long id;
	
	private String name;
	private BigDecimal intialPrice;
	private Date auctionEnd;
	
	@OneToMany(mappedBy="item",
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
	private Set<Bid> bids = new HashSet<Bid>();
	
	public Item() {
		super();
	}

	public Item(String name, BigDecimal intialPrice, Date auctionEnd) {
		super();
		this.name = name;
		this.intialPrice = intialPrice;
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

	public BigDecimal getIntialPrice() {
		return intialPrice;
	}

	public void setIntialPrice(BigDecimal intialPrice) {
		this.intialPrice = intialPrice;
	}

	public Date getAuctionEnd() {
		return auctionEnd;
	}

	public void setAuctionEnd(Date auctionEnd) {
		this.auctionEnd = auctionEnd;
	}

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}
	
}
