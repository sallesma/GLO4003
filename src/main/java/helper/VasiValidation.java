package helper;


public class VasiValidation extends AbstractCreditCardValidation {

	public VasiValidation(String cardType, String cardOwner, String cardNumber, String expirationMonth, String expirationYear,
			String cardCode) {
		super(cardType, cardOwner, cardNumber, expirationMonth, expirationYear, cardCode);
	}

	@Override
	public boolean isNameValid() {
		// Here we suppose that a verification is really done
		return true;
	}

	@Override
	public boolean isNumberValid() {
		// Here we suppose that a verification is really done
		return true;
	}

	@Override
	public boolean isExpirationValid() {
		// Here we suppose that a verification is really done
		return true;
	}

}
