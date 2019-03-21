package org.learning.hibernate.managingdata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	private Long id;
	
	private String name;
	
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
}
