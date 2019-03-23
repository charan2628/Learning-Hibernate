package org.learning.hibernate.queries;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BA")
public class BankAccount extends BillingDetails{
	
	private String account;
	private String bankName;
	private String swift;
	
	public BankAccount() {
		super();
	}

	public BankAccount(String account, String bankName, String swift) {
		super();
		this.account = account;
		this.bankName = bankName;
		this.swift = swift;
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
