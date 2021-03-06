package org.learning.hibernate.queries;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="BD_TYPE")
public abstract class BillingDetails {

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	protected Long id;
	protected String owner;
	
	public BillingDetails() {
		super();
	}

	public BillingDetails(String owner) {
		super();
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
