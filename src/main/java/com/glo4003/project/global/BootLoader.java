package com.glo4003.project.global;


import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import com.glo4003.project.match.controller.MatchController;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.match.viewModel.MatchConverter;
import com.glo4003.project.user.dao.UserModelDao;
import com.glo4003.project.user.helper.UserConverter;



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
	    
	    
	    
	    //server.addBean(home);
	    server.join();
	    


	    
	  }

}
