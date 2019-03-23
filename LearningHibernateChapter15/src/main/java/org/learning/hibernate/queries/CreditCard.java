package org.learning.hibernate.queries;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
public class CreditCard extends BillingDetails{

	private String number;
	private String expMonth;
	private String expYear;
	
	public CreditCard() {
		super();
	}

	public CreditCard(String number, String expMonth, String expYear) {
		super();
		this.number = number;
		this.expMonth = expMonth;
		this.expYear = expYear;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
