package org.learning.hibernate.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
@Access(AccessType.FIELD)
public class User {

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	protected Long id;
	
	protected Address homeAddress;

	public User(Address homeAddress) {
		super();
		this.homeAddress = homeAddress;
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
	
}