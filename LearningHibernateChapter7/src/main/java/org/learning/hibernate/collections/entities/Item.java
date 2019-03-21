package org.learning.hibernate.collections.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Item {

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	private Long id;
	
	private String name;
	
	@OneToMany(mappedBy="item", cascade= {CascadeType.PERSIST}, orphanRemoval = true)
	protected Set<Bid> bids = new HashSet<>();
	
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

	public Set<Bid> getBids() {
		return bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}
	
}
