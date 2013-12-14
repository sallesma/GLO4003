package com.glo4003.project.global;


import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

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
	    
	    for (Object o : controllers.values()) {
			((ControllerInterface)o).dependanciesInjection();
		}
	    	    
	    server.join();	    
	  }

}
