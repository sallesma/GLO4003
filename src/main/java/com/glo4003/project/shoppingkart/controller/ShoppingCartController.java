package com.glo4003.project.shoppingkart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.glo4003.project.database.dto.MatchDto;
import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.global.ControllerInterface;
import com.glo4003.project.injection.Resolver;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.match.helper.MatchConverter;
import com.glo4003.project.shoppingkart.cardValidation.AbstractCreditCardValidation;
import com.glo4003.project.shoppingkart.cardValidation.AmericanExpressoValidation;
import com.glo4003.project.shoppingkart.cardValidation.MistercardValidation;
import com.glo4003.project.shoppingkart.cardValidation.VasiValidation;
import com.glo4003.project.ticket.helper.InstantiateTicketConverter;
import com.glo4003.project.ticket.model.InstantiateAbstractTicket;
import com.glo4003.project.ticket.model.InstantiateGeneralAdmissionTicket;
import com.glo4003.project.ticket.model.InstantiateReservedTicket;
import com.glo4003.project.ticket.viewModel.InstantiateGeneralAdmissionTicketViewModel;
import com.glo4003.project.ticket.viewModel.InstantiateReservedTicketViewModel;
import com.glo4003.project.ticket.viewModel.InstantiateTicketViewModel;
import com.glo4003.project.user.dao.UserModelDao;
import com.glo4003.project.user.helper.UserConverter;
import com.glo4003.project.user.model.UserConcreteModel;
import com.glo4003.project.user.model.view.LoginViewModel;
import com.glo4003.project.user.model.view.UserViewModel;
import com.glo4003.project.shoppingkart.mail.Email;

@Controller
public class ShoppingCartController implements ControllerInterface {
	
	private UserConverter userConverter;
	private MatchModelDao matchDao;
	private InstantiateTicketConverter ticketConverter;
	private List<InstantiateTicketViewModel> billTickets;	
	
	public void dependanciesInjection(MatchModelDao matchDao ,UserConverter userConverter,InstantiateTicketConverter ticketConverter ){ 
		this.matchDao = matchDao ;
		this.userConverter = userConverter;
		this.billTickets = new ArrayList<>();
		this.ticketConverter = ticketConverter;
	}
	
	
	public void dependanciesInjection() {
		this.matchDao = Resolver.getInjectedInstance(MatchModelDao.class);
		this.userConverter = Resolver.getInjectedInstance(UserConverter.class);
		this.ticketConverter = Resolver.getInjectedInstance(InstantiateTicketConverter.class);
		this.billTickets = new ArrayList<>();
	}
	
	@RequestMapping(value = "/selectedTicketsAction", method = RequestMethod.POST)
	public String handlePosts(Model model, HttpServletRequest request, @RequestParam String action) throws PersistException {	
		String[] selectedTickets =  request.getParameterValues("ticketId");
		UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("loggedUser");
		UserConcreteModel userModel = userConverter.convertFromView(userViewModel);
		
		if(action.equals("buy")){
			
			if (selectedTickets == null) {
				model.addAttribute("noTicket", "Il n'y a pas de billets sélectionnés, la facture n'a pas pu être éditée.");
				model.addAttribute("user", userViewModel);
				model.addAttribute("entry", new LoginViewModel());	
				return "shoppingCart";
			} else {
				for ( String selectedTicket : selectedTickets ) {
					InstantiateAbstractTicket aTicket = userModel.getTicketById(Integer.valueOf(selectedTicket));
					if (aTicket instanceof InstantiateGeneralAdmissionTicket) {
						InstantiateGeneralAdmissionTicket tGA = (InstantiateGeneralAdmissionTicket) aTicket;
						InstantiateGeneralAdmissionTicketViewModel tVM = ticketConverter.convert(tGA);
						billTickets.add(tVM);
					}
					else if (aTicket instanceof InstantiateReservedTicket) {
						InstantiateReservedTicket tRes = (InstantiateReservedTicket) aTicket;
						InstantiateReservedTicketViewModel tVM = ticketConverter.convert(tRes);
						billTickets.add(tVM);
					}
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
					MatchDto match;
					try {
						match = matchDao.getById(matchId);
						userModel.deleteTicketAndReplaceInMatch(Integer.valueOf(selectedTicket), match);
						matchDao.save(match);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			userViewModel = userConverter.convertToView(userModel);
			request.getSession().setAttribute("loggedUser", userViewModel);
			model.addAttribute("user", userViewModel);
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
	public String payment_done(Model model, HttpServletRequest request) throws PersistException {
		UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("loggedUser");
		UserConcreteModel userModel = userConverter.convertFromView(userViewModel);
		
		String cardType = (String) request.getParameter("cardType");
		String cardOwner = (String) request.getParameter("cardOwner");
		String cardNumber = (String) request.getParameter("cardNumber");
		String expirationMonth = (String) request.getParameter("expirationMonth");
		String expirationYear = (String) request.getParameter("expirationYear");
		String cardCode = (String) request.getParameter("cardCode");
		
		Email mail = new Email(userModel);
		
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
				
				//Adding ticketList into mail to send
				String mailContent = new String("Sport\tDate\tAdversaire\tVille\tTerrain\n\n");
				for(InstantiateTicketViewModel ticket : billTickets){
					mailContent += ticket.getSport() + "\t";
					mailContent += ticket.getDate() + "\t";
					mailContent += ticket.getOpponent() + "\t";
					mailContent += ticket.getCity() + "\t";
					mailContent += ticket.getField() + "\n";
				}
				
				mail.setContent(mailContent);
				for (InstantiateTicketViewModel ticket : billTickets) {
					if (ticket instanceof InstantiateGeneralAdmissionTicketViewModel) {
						InstantiateGeneralAdmissionTicketViewModel tGAVM = (InstantiateGeneralAdmissionTicketViewModel)ticket;
						InstantiateGeneralAdmissionTicket tGA = ticketConverter.convert(tGAVM);
						userModel.deleteTicketById(tGA.getId());
					}
					else if (ticket instanceof InstantiateReservedTicketViewModel) {
						InstantiateReservedTicketViewModel tRVM = (InstantiateReservedTicketViewModel)ticket;
						InstantiateReservedTicket tR = ticketConverter.convert(tRVM);
						userModel.deleteTicketById(tR.getId());
					}	
				}
			}
			model.addAttribute("paymentOk", true);
			
			//Sending confirmation eMail to user
			mail.sendFromGMail();
		}
		else {
			model.addAttribute("paymentOk", false);
		}
		this.billTickets.clear();
		userViewModel = userConverter.convertToView(userModel);
		request.getSession().setAttribute("loggedUser", userViewModel);
		model.addAttribute("user", userViewModel);
		model.addAttribute("entry", new LoginViewModel());
		
		return "shoppingCart";
	}
	
	
	@RequestMapping(value = "/modifyTicket", method = RequestMethod.GET)
	public String modifyTicket(Model model, HttpServletRequest request) throws PersistException, ConvertException {
		UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("loggedUser");
		UserConcreteModel userModel = userConverter.convertFromView(userViewModel);
		
		int ticketId = Integer.valueOf(request.getParameter("id"));
		
		InstantiateAbstractTicket t = userModel.getTicketById(ticketId);
		if (t instanceof InstantiateGeneralAdmissionTicket ) {
			int newNbPlaces = Integer.valueOf(request.getParameter("nbPlaceInput"));
			InstantiateGeneralAdmissionTicket tGAT = (InstantiateGeneralAdmissionTicket)t;
			MatchDto match = matchDao.getById(tGAT.getMatch().getId());
			
			if (!tGAT.changeNbPlaces(newNbPlaces, match)) {
				model.addAttribute("impossibleChange", "Changement impossible, pas assez de place libres");
			}
			matchDao.save(match);
			userViewModel = userConverter.convertToView(userModel);
			request.getSession().setAttribute("loggedUser", userViewModel);
			model.addAttribute("user", request.getSession().getAttribute("loggedUser"));
			model.addAttribute("entry", new LoginViewModel());
			return "shoppingCart";
		}
		else {
			String newPlacement = request.getParameter("placement");
			InstantiateReservedTicket tRT = (InstantiateReservedTicket)t;
			MatchDto match = matchDao.getById(tRT.getMatch().getId());
			
			if (!tRT.changePlace(newPlacement, match)) {
				model.addAttribute("impossibleChange", "Changement impossible, pas assez de place libres");
			}
			matchDao.save(match);
			userViewModel = userConverter.convertToView(userModel);
			request.getSession().setAttribute("loggedUser", userViewModel);
			model.addAttribute("user", request.getSession().getAttribute("loggedUser"));
			model.addAttribute("entry", new LoginViewModel());
			return "shoppingCart";
		}
		
	}
	
	@RequestMapping(value = "/emptyCart", method = RequestMethod.GET)
	public String emptyCart(Model model, HttpServletRequest request) throws PersistException {
		UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("loggedUser");
		UserConcreteModel userModel = userConverter.convertFromView(userViewModel);
		this.billTickets.clear();
		try {
			userModel.emptyCartAndReplaceTickets(matchDao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		userViewModel = userConverter.convertToView(userModel);
		request.getSession().setAttribute("loggedUser", userViewModel);
		return "shoppingCart";
	}
	
	public void replaceUserConverter(UserConverter converter) {
		this.userConverter = converter;
	}
	public void replaceMatchDAO(MatchModelDao dao) {
		this.matchDao = dao;
	}

	
}