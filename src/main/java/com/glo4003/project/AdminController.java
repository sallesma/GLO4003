package com.glo4003.project;

import model.MatchModel;

import javax.servlet.http.HttpServletRequest;

import model.AbstractTicketCategory;
import model.LoginViewModel;
import model.ReservedTicketCategory;
import model.UserViewModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import database.DbHelper;

@Controller
public class AdminController {
  
	@RequestMapping(value = "/addPlacementTickets", method = RequestMethod.GET)
	public String addPlacementTickets(Model model,HttpServletRequest request) {		
		String newTicket = request.getParameter("newTicket");
		String categoryName = request.getParameter("category");
		DbHelper dbHelper = DbHelper.getInstance();
		int id = Integer.valueOf(request.getParameter("matchID"));
		MatchModel match = dbHelper.getMatchFromId(id);
		for ( AbstractTicketCategory cat : match.getTickets() ) {
			if ( cat.getName().equals(categoryName) )
				if ( !((ReservedTicketCategory) cat).getPlacements().contains(newTicket) ) {
					((ReservedTicketCategory) cat).getPlacements().add(newTicket);
					cat.setNumberInitialTickets(cat.getNumberInitialTickets()+1);
				}
		}
		
		model.addAttribute("match", match);
		return "match";
	}
	
	@RequestMapping(value = "/addGeneralTickets", method = RequestMethod.GET)
	public String addGeneralTickets(Model model ,HttpServletRequest request) {	
		
		String categoryName = request.getParameter("category");
		DbHelper dbHelper = DbHelper.getInstance();
		int id = Integer.valueOf(request.getParameter("matchID"));
		int ticketNumber = Integer.valueOf(request.getParameter("ticketNumber"));	
		MatchModel match = dbHelper.getMatchFromId(id);
		for ( AbstractTicketCategory cat : match.getTickets() ) {
			if ( cat.getName().equals(categoryName) ) {
					
					cat.setNumberInitialTickets(cat.getNumberInitialTickets()+ticketNumber);
				}
		}
		model.addAttribute("match", match);
		return "match";
	}
}
