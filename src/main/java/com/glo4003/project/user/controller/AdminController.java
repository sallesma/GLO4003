package com.glo4003.project.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.AbstractTicketCategory;
import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.database.model.ReservedTicketCategory;
import com.glo4003.project.match.dao.MatchModelDao;

@Controller
public class AdminController {
	
	private MatchModelDao matchDao = new MatchModelDao();
  
	@RequestMapping(value = "/addPlacementTickets", method = RequestMethod.GET)
	public String addPlacementTickets(Model model,HttpServletRequest request) throws PersistException {		
		String newTicket = request.getParameter("newTicket");
		String categoryName = request.getParameter("category");		
		Long id = Long.valueOf(request.getParameter("matchID"));
		MatchModel match = matchDao.getById(id);
		for ( AbstractTicketCategory cat : match.getTickets() ) {
			if ( cat.getName().equals(categoryName) )
				if ( !((ReservedTicketCategory) cat).getPlacements().contains(newTicket) ) {
					((ReservedTicketCategory) cat).getPlacements().add(newTicket);
					cat.setNumberInitialTickets(cat.getNumberInitialTickets()+1);
				}
		}
		model.addAttribute("match", match);
		MatchModelDao matchModelDao = new MatchModelDao();
		try {
			matchModelDao.save(match);
		} catch (ConvertException e) {
			e.printStackTrace();
		}
		return "match";
	}
	
	@RequestMapping(value = "/addGeneralTickets", method = RequestMethod.GET)
	public String addGeneralTickets(Model model ,HttpServletRequest request) throws PersistException {	
		
		String categoryName = request.getParameter("category");
		
		Long id = Long.valueOf(request.getParameter("matchID"));
		int ticketNumber = Integer.valueOf(request.getParameter("ticketNumber"));	
		MatchModel match = matchDao.getById(id);
		for ( AbstractTicketCategory cat : match.getTickets() ) {
			if ( cat.getName().equals(categoryName) ) {
					
					cat.setNumberInitialTickets(cat.getNumberInitialTickets()+ticketNumber);
				}
		}
		model.addAttribute("match", match);
		MatchModelDao matchModelDao = new MatchModelDao();
		try {
			matchModelDao.save(match);
		} catch (ConvertException e) {
			e.printStackTrace();
		}
		return "match";
	}
	
	public void replaceMatchDAO(MatchModelDao dao) {
		this.matchDao = dao;
	}
}
