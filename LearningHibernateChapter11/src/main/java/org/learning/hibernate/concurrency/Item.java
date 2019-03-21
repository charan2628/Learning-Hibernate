package org.learning.hibernate.concurrency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Item {

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	private Long id;
	
	@Version
	private long version;
	
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

	public long getVersion() {
		return version;
	}
}
