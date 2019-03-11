package org.learning.hibernate.mappedsuperclass;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BillingDetails {

	protected String owner;

	public BillingDetails() {
		super();
	}

	public BillingDetails(String owner) {
		super();
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
