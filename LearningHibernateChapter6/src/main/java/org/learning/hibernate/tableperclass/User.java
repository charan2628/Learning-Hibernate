package org.learning.hibernate.tableperclass;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
@Access(AccessType.FIELD)
public class User {

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	protected Long id;
	
	protected Address homeAddress;
	
	@ManyToOne
	@JoinColumn(name="biling_id", referencedColumnName="id")
	protected BillingDetails billingDetails;

	public User(Address homeAddress, BillingDetails billingDetails) {
		super();
		this.homeAddress = homeAddress;
		this.billingDetails = billingDetails;
	}

	public User() {
		super();
	}

	public Address getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BillingDetails getBillingDetails() {
		return billingDetails;
	}

	public void setBillingDetails(BillingDetails billingDetails) {
		this.billingDetails = billingDetails;
	}
	
}