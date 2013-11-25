package com.glo4003.project.logging;

public aspect ShoppingCartLoggingAspect extends AspectLogging {
	
	public ShoppingCartLoggingAspect() {
		super("shoppingCart.log", ShoppingCartLoggingAspect.class);
	}

	private pointcut shoppingCartPaymentDone():
		execution ( * *..ShoppingCartController.payment_done(..) );
	
	after() : shoppingCartPaymentDone() {
		LogName(thisJoinPoint);
	}
}
