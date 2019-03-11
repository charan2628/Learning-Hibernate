package org.learning.hibernate.mappedsuperclass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BankAccount extends BillingDetails{

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	protected Long id;
	protected String account;
	protected String bankName;
	protected String swift;
	
	public BankAccount() {
		super();
	}

	public BankAccount(String account, String bankName, String swift) {
		super();
		this.account = account;
		this.bankName = bankName;
		this.swift = swift;
	}

	public Long getId() {
		return id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}
	
}
