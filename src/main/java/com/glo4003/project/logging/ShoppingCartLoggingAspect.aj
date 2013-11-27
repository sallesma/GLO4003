package com.glo4003.project.logging;

import javax.servlet.http.HttpServletRequest;

public aspect ShoppingCartLoggingAspect extends AspectLogging {
	
	public ShoppingCartLoggingAspect() {
		super("shoppingCart.log", ShoppingCartLoggingAspect.class);
	}

	private pointcut shoppingCartPaymentDone():
		execution ( * *..ShoppingCartController.payment_done(..) );
	
	
	after() : shoppingCartPaymentDone() {
		for (Object arg : thisJoinPoint.getArgs()) {
			if(arg instanceof HttpServletRequest)
			{
				HttpServletRequest request = (HttpServletRequest) arg;
				logInfo(thisJoinPoint, request.getParameterMap());
			}
		}
	}
}
