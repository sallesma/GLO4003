package com.glo4003.project.global;


import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

import com.glo4003.project.injection.Resolver;
import com.glo4003.project.match.controller.MatchController;
import com.glo4003.project.shoppingkart.controller.ShoppingCartController;
import com.glo4003.project.user.controller.AdminController;
import com.glo4003.project.user.controller.UserController;

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
	    controllers.put("matchController", Resolver.getInjectedInstance(MatchController.class));
	    controllers.put("shoppingCartController", Resolver.getInjectedInstance(ShoppingCartController.class));
	    controllers.put("adminController", Resolver.getInjectedInstance(AdminController.class));
	    controllers.put("userController", Resolver.getInjectedInstance(UserController.class));	    
	    
	    server.join();	    
	  }

}
