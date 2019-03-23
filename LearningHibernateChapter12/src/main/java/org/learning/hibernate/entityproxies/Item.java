package org.learning.hibernate.entityproxies;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Item {

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	private Long id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name="SELLER_ID")
	private User seller;
	
	public Item() {
		super();
	}

	public Item(String name, User seller) {
		super();
		this.name = name;
		this.seller = seller;
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

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}
	
}
