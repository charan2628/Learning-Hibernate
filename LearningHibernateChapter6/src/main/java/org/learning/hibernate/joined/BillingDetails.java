package org.learning.hibernate.joined;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class BillingDetails {

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	private Long id;
	
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

	public Long getId() {
		return id;
	}

}
