package com.glo4003.project.aspect;

public aspect ShoppingCartLoggingAspect extends AspectLogging {
	
	public ShoppingCartLoggingAspect() {
		super("shoppingCart.log");
	}

	private pointcut shoppingCartPaymentDone():
		execution ( * *..ShoppingCartController.payment_done(..) );
	
	after() : shoppingCartPaymentDone() {
		//LogName(thisJoinPoint, "shoppingCart.log");
	}
}
