package com.glo4003.project.shoppingkart.cardValidation;

public abstract class AbstractCreditCardValidation {
	private String cardType;
	private String cardOwner;
	private String cardNumber;
	private String expirationMonth;
	private String expirationYear;
	private String cardCode;
	
	public AbstractCreditCardValidation(String cardType, String cardOwner, String cardNumber, String expirationMonth, String expirationYear, String cardCode) {
		super();
		this.cardType = cardType;
		this.cardOwner = cardOwner;
		this.cardNumber = cardNumber;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.cardCode = cardCode;
	}
	
	public final boolean isValid() {
		if (isNameValid() && isNumberValid() && isExpirationValid()) {
			return true;
		} else {
			return false;
		}
	}

	public abstract boolean isNameValid();

	public abstract boolean isNumberValid();

	public abstract boolean isExpirationValid();

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardOwner() {
		return cardOwner;
	}

	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public String getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
}
