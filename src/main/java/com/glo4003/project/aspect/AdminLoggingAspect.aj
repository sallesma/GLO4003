package com.glo4003.project.aspect;


public aspect AdminLoggingAspect extends AspectLogging {
	
	public AdminLoggingAspect() {
		super("admin.log");
	}

	private pointcut adminControllerAddPlacementTicket():
		execution ( * com.glo4003.project.user.controller.AdminController.addPlacementTickets(..) );
	
	private pointcut adminControllerAddGeneralTicket():
		execution ( * com.glo4003.project.user.controller.AdminController.addGeneralTickets(..) );
	
	after() : adminControllerAddPlacementTicket() || adminControllerAddGeneralTicket() {
		LogName(thisJoinPoint);
	}
}
