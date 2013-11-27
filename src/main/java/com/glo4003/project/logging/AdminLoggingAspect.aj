package com.glo4003.project.logging;

import javax.servlet.http.HttpServletRequest;


public aspect AdminLoggingAspect extends AspectLogging {
	
	public AdminLoggingAspect() {
		super("admin.log", AdminLoggingAspect.class);
	}

	private pointcut adminControllerAddPlacementTicket():
		execution ( * com.glo4003.project.user.controller.AdminController.addPlacementTickets(..) );
	
	private pointcut adminControllerAddGeneralTicket():
		execution ( * com.glo4003.project.user.controller.AdminController.addGeneralTickets(..) );
	
	after() : adminControllerAddPlacementTicket() || adminControllerAddGeneralTicket() {		
		for (Object arg : thisJoinPoint.getArgs()) {
			if(arg instanceof HttpServletRequest)
			{
				HttpServletRequest request = (HttpServletRequest) arg;
				logInfo(thisJoinPoint, request.getParameterMap());
			}
		}
	}
}
