package com.glo4003.project.shoppingkart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.InstantiateGeneralAdmissionTicket;
import model.InstantiateReservedTicket;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.database.model.UserModel;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.shoppingkart.cardValidation.AbstractCreditCardValidation;
import com.glo4003.project.shoppingkart.cardValidation.AmericanExpressoValidation;
import com.glo4003.project.shoppingkart.cardValidation.MistercardValidation;
import com.glo4003.project.shoppingkart.cardValidation.VasiValidation;
import com.glo4003.project.ticket.model.InstantiateAbstractTicket;
import com.glo4003.project.user.helper.UserConverter;
import com.glo4003.project.user.model.view.LoginViewModel;
import com.glo4003.project.user.model.view.UserViewModel;

@Controller
public class ShoppingCartController {
	private UserConverter userConverter = new UserConverter();
	private MatchModelDao matchDao = new MatchModelDao();
	private List<InstantiateAbstractTicket> billTickets;

	@RequestMapping(value = "/selectedTicketsAction", method = RequestMethod.POST)
	public String handlePosts(Model model, HttpServletRequest request, @RequestParam String action) {	

		UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("loggedUser");
		UserModel userModel = userConverter.convert(userViewModel);

		String[] selectedTickets =  request.getParameterValues("ticketId");
		if( action.equals("buy") ){

			billTickets = new ArrayList<InstantiateAbstractTicket>();

			if (selectedTickets == null) {
				model.addAttribute("noTicket", "Il n'y a pas de billets sélectionnés, la facture n'a pas pu être éditée.");
				model.addAttribute("user", request.getSession().getAttribute("loggedUser"));
				model.addAttribute("entry", new LoginViewModel());	
				return "shoppingCart";
			} else {
				for ( String selectedTicket : selectedTickets ) {
					billTickets.add( userModel.getTicketById(Integer.valueOf(selectedTicket)) );
				}
				model.addAttribute("billTickets", billTickets);
				model.addAttribute("user", request.getSession().getAttribute("loggedUser"));
				model.addAttribute("entry", new LoginViewModel());	
				
				return "bill";
			}
		}
		else if( action.equals("delete") ){

			if (selectedTickets == null) {
				model.addAttribute("noTicket", "Il n'y a pas de billets sélectionnés");
			} else {
				for ( String selectedTicket : selectedTickets ) {
					Long matchId = userModel.getTicketById(Integer.valueOf(selectedTicket)).getMatch().getId();
					MatchModel match;
					try {
						match = matchDao.getById(matchId);
						userModel.deleteTicketAndReplaceInMatch(Integer.valueOf(selectedTicket), match);
						matchDao.save(match);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			model.addAttribute("user", request.getSession().getAttribute("loggedUser"));
			model.addAttribute("entry", new LoginViewModel());

			return "redirect:/shoppingCart";
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String payment(Model model, HttpServletRequest request) {	

		model.addAttribute("billTickets", request.getSession().getAttribute("billTickets"));
		model.addAttribute("user", request.getSession().getAttribute("loggedUser"));
		model.addAttribute("entry", new LoginViewModel());	

		return "payment";
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	public String payment_done(Model model, HttpServletRequest request) {
		UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("loggedUser");
		UserModel userModel = userConverter.convert(userViewModel);
		
		String cardType = (String) request.getParameter("cardType");
		String cardOwner = (String) request.getParameter("cardOwner");
		String cardNumber = (String) request.getParameter("cardNumber");
		String expirationMonth = (String) request.getParameter("expirationMonth");
		String expirationYear = (String) request.getParameter("expirationYear");
		String cardCode = (String) request.getParameter("cardCode");
		
		AbstractCreditCardValidation validator = null;
		switch (cardType) {
		case "Vasi":
			validator = new VasiValidation(cardType, cardOwner, cardNumber, expirationMonth, expirationYear, cardCode);
			break;
		case "Mistercard":
			validator = new MistercardValidation(cardType, cardOwner, cardNumber, expirationMonth, expirationYear, cardCode);
			break;
		case "AmericanExpresso":
			validator = new AmericanExpressoValidation(cardType, cardOwner, cardNumber, expirationMonth, expirationYear, cardCode);
			break;
		default:
			return "redirect:/shoppingCart";
		}
		
		if (validator.isValid()) {
			if(billTickets != null) {
				for (InstantiateAbstractTicket ticket : billTickets) {
					userModel.deleteTicket(ticket);
				}
			}
		}
		
		model.addAttribute("user", request.getSession().getAttribute("loggedUser"));
		model.addAttribute("entry", new LoginViewModel());
		
		return "shoppingCart";
	}
	
	
	@RequestMapping(value = "/modifyTicket", method = RequestMethod.GET)
	public String modifyTicket(Model model, HttpServletRequest request) throws PersistException, ConvertException {
		UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("loggedUser");
		UserModel userModel = userConverter.convert(userViewModel);
		int ticketId = Integer.valueOf(request.getParameter("id"));
		
		InstantiateAbstractTicket t = userModel.getTicketById(ticketId);
		if (t instanceof InstantiateGeneralAdmissionTicket ) {
			int newNbPlaces = Integer.valueOf(request.getParameter("nbPlaceInput"));
			InstantiateGeneralAdmissionTicket tGAT = (InstantiateGeneralAdmissionTicket)t;
			MatchModel match = matchDao.getById(tGAT.getMatch().getId());
			
			if (!tGAT.changeNbPlaces(newNbPlaces, match)) {
				model.addAttribute("impossibleChange", "Changement impossible, pas assez de place libres");
			}
			matchDao.save(match);
			model.addAttribute("user", request.getSession().getAttribute("loggedUser"));
			model.addAttribute("entry", new LoginViewModel());
			return "shoppingCart";
		}
		else {
			String newPlacement = request.getParameter("placement");
			InstantiateReservedTicket tRT = (InstantiateReservedTicket)t;
			MatchModel match = matchDao.getById(tRT.getMatch().getId());
			
			if (!tRT.changePlace(newPlacement, match)) {
				model.addAttribute("impossibleChange", "Changement impossible, pas assez de place libres");
			}
			matchDao.save(match);
			model.addAttribute("user", request.getSession().getAttribute("loggedUser"));
			model.addAttribute("entry", new LoginViewModel());
			return "shoppingCart";
		}
		
	}
	
	@RequestMapping(value = "/emptyCart", method = RequestMethod.GET)
	public String emptyCart(Model model, HttpServletRequest request) {
		UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("loggedUser");
		UserModel userModel = userConverter.convert(userViewModel);

		try {
			userModel.emptyCartAndReplaceTickets(matchDao);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "shoppingCart";
	}
	  
	public void replaceUserConverter(UserConverter converter) {
		this.userConverter = converter;
	}
	public void replaceMatchDAO(MatchModelDao dao) {
		this.matchDao = dao;
	}
}