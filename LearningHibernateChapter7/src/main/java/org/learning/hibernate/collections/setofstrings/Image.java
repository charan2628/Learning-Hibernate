package org.learning.hibernate.collections.setofstrings;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Image implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -479236313205938403L;

	@Id
	@ManyToOne
	protected Item item;
	
	@Id
	protected String fileName;

	public Image() {
		super();
	}

	public Image(Item item, String fileName) {
		super();
		this.item = item;
		this.fileName = fileName;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
