package org.learning.hibernate.joined;

import javax.persistence.Entity;

@Entity
public class CreditCard extends BillingDetails{

	
	protected String cardNumber;
	
	protected String expMonth;
	
	protected String expYear;

	public CreditCard() {
		super();
	}

	public CreditCard(String cardNumber, String expMonth, String expYear) {
		super();
		this.cardNumber = cardNumber;
		this.expMonth = expMonth;
		this.expYear = expYear;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}

	public String getExpYear() {
		return expYear;
	}

	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}
	
	
}
