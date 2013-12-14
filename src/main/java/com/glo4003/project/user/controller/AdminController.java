package com.glo4003.project.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glo4003.project.database.dto.AbstractTicketCategory;
import com.glo4003.project.database.dto.MatchDto;
import com.glo4003.project.database.dto.ReservedTicketCategoryDto;
import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.global.ControllerInterface;
import com.glo4003.project.injection.Resolver;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.match.helper.MatchConverter;
import com.glo4003.project.match.model.MatchConcreteModel;

@Controller
public class AdminController implements ControllerInterface {
	
	private MatchModelDao matchDao;
	private MatchConverter mConverter;
	
	public void dependanciesInjection()
	{
		this.matchDao = Resolver.getInjectedInstance(MatchModelDao.class);
		this.mConverter = Resolver.getInjectedInstance(MatchConverter.class);
	}
	
	
	// Uniquement pour les tests
	public void dependanciesInjection(MatchModelDao matchDao)
	{
		this.matchDao = matchDao;
	}
	
	@RequestMapping(value = "/addPlacementTickets", method = RequestMethod.GET)
	public String addPlacementTickets(Model model,HttpServletRequest request) throws PersistException {		
		String newTicket = request.getParameter("newTicket");
		String categoryName = request.getParameter("category");		
		Long id = Long.valueOf(request.getParameter("matchID"));
		MatchDto matchDto = matchDao.getById(id);
		MatchConcreteModel match = mConverter.convertFromDB(matchDto);
		for ( AbstractTicketCategory cat : match.getTickets() ) {
			if ( cat.getName().equals(categoryName) )
				if ( !((ReservedTicketCategoryDto) cat).getPlacements().contains(newTicket) ) {
					((ReservedTicketCategoryDto) cat).getPlacements().add(newTicket);
					cat.setNumberInitialTickets(cat.getNumberInitialTickets()+1);
				}
		}
		model.addAttribute("match", match);
		matchDto = mConverter.convertToDB(match);
		try {
			matchDao.save(matchDto);
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
		MatchDto matchDto = matchDao.getById(id);
		MatchConcreteModel match = mConverter.convertFromDB(matchDto);
		for ( AbstractTicketCategory cat : match.getTickets() ) {
			if ( cat.getName().equals(categoryName) ) {
					
					cat.setNumberInitialTickets(cat.getNumberInitialTickets()+ticketNumber);
				}
		}
		model.addAttribute("match", match);
		matchDto = mConverter.convertToDB(match);
		try {
			matchDao.save(matchDto);
		} catch (ConvertException e) {
			e.printStackTrace();
		}
		return "match";
	}
	
	public void replaceMatchDAO(MatchModelDao dao) {
		this.matchDao = dao;
	}
}
