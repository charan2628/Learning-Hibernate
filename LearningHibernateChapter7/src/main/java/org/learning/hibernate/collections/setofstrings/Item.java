package org.learning.hibernate.collections.setofstrings;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Item {

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	private Long id;
	
	private String name;
	
	@ElementCollection
	@CollectionTable(
			name = "IMAGE",
			joinColumns = @JoinColumn(name = "ITEM_ID")
			)
	@Column(name = "FILENAME")
	private Set<String> images = new HashSet<>();
	
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

	public Set<String> getImages() {
		return images;
	}
	
	
}
