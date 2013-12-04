package com.glo4003.project.global;


import java.util.ArrayList;
import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

import com.glo4003.project.match.controller.MatchController;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.match.helper.MatchConverter;
import com.glo4003.project.shoppingkart.controller.ShoppingCartController;
import com.glo4003.project.ticket.helper.InstantiateTicketConverter;
import com.glo4003.project.ticket.viewModel.InstantiateTicketViewModel;
import com.glo4003.project.user.controller.AdminController;
import com.glo4003.project.user.controller.UserController;
import com.glo4003.project.user.dao.UserModelDao;
import com.glo4003.project.user.helper.UserConverter;
import com.glo4003.project.user.service.UserService;



public class BootLoader {

	
	public static void main(String[] args) throws Exception {
		
	    Server server = new Server();


	    
	    
	    SelectChannelConnector connector = new SelectChannelConnector();
	    connector.setPort(8080);
	    server.addConnector(connector);

	    WebAppContext webApp = new WebAppContext();
	    webApp.setContextPath("/");
	    webApp.setWar("src/main/webapp");
	    
	    server.setHandler(webApp);
	    server.start();
	    
	    Map<String, Object> controllers = ContextProvider.getApplicationControllers();
	    
	    ((MatchController)controllers.get("matchController")).dependanciesInjection(new MatchModelDao(), new UserModelDao(), new MatchConverter(), new UserConverter());
	    ((ShoppingCartController)controllers.get("shoppingCartController")).dependanciesInjection(new MatchModelDao(), new UserConverter(), new InstantiateTicketConverter(), new ArrayList<InstantiateTicketViewModel>());
	    ((AdminController)controllers.get("adminController")).dependanciesInjection(new MatchModelDao());
	    UserService userService = new UserService(new UserModelDao(), new  UserConverter(), new ModelValidator());
	    ((UserController)controllers.get("userController")).dependanciesInjection(userService);
	    
	    
	    //server.addBean(home);
	    server.join();
	    


	    
	  }

}
