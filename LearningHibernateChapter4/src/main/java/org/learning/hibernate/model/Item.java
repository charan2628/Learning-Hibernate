package org.learning.hibernate.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@org.hibernate.annotations.DynamicInsert
@org.hibernate.annotations.DynamicUpdate
public class Item {

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	private Long id;
	
	private String name;

	@org.hibernate.annotations.Formula(
			"(select avg(b.amount) from Bid b where b.item_id_no = id)"
			)
	private BigDecimal averageBidAmount;
	
	public Item() {
		super();
	}

	public Item(String name) {
		super();
		this.name = name;
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

	public BigDecimal getAverageBidAmount() {
		return averageBidAmount;
	}

}
