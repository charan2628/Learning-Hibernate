package org.learning.hibernate.mappedsuperclass;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AttributeOverride(
		name="owner",
		column=@Column(name="CC_OWNER")
		)
public class CreditCard extends BillingDetails{

	@Id
	@GeneratedValue(generator="ID_GENERATOR")
	protected Long id;
	
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

	public Long getId() {
		return id;
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
