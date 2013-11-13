package com.glo4003.project;

import helper.UserConverter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.InstantiateAbstractTicket;
import model.InstantiateTicketModel;
import model.LoginViewModel;
import model.MatchModel;
import model.UserModel;
import model.UserViewModel;

import org.junit.runner.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import database.dao.MatchModelDao;

@Controller
public class PaymentController {
	private UserConverter userConverter;
	private MatchModelDao matchDao = new MatchModelDao();
	private UserConverter uConverter;
	List<InstantiateAbstractTicket> billTickets;
	
	@RequestMapping(value = "/bill", method = RequestMethod.POST)
	public String bill(Model model, HttpServletRequest request) {	
		
		UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("loggedUser");
        userConverter = new UserConverter();
        UserModel userModel = userConverter.convert(userViewModel);
        
        /*List<InstantiateAbstractTicket>*/ billTickets = new ArrayList<InstantiateAbstractTicket>();
        
        String[] selectedTickets =  request.getParameterValues("ticketId");
        if (selectedTickets == null) {
			model.addAttribute("noTicket", "Il n'y a pas de billets sélectionnés, la facture n'a pas pu être éditée.");
        } else {
			for ( String selectedTicket : selectedTickets ) {
	        	billTickets.add( userModel.getTicketById(Integer.valueOf(selectedTicket)) );
	        }
	        model.addAttribute("billTickets", billTickets);
        }
        model.addAttribute("user", request.getSession().getAttribute("loggedUser"));
		model.addAttribute("entry", new LoginViewModel());	
		
		return "bill";
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String payment(Model model, HttpServletRequest request) {	
		
		model.addAttribute("billTickets", request.getSession().getAttribute("billTickets"));
		model.addAttribute("user", request.getSession().getAttribute("loggedUser"));
		model.addAttribute("entry", new LoginViewModel());	
		
		return "payment";
	}
	
	@RequestMapping(value="/payment", method = RequestMethod.POST)
	public String payment_done(Model model, HttpServletRequest request){
		//substituer billets acheter au panier
		//Get current logged user
        UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("loggedUser");
        uConverter = new UserConverter();
        UserModel userModel = uConverter.convert(userViewModel);
        
        //Get ticket list from the bill
        //ArrayList<InstantiateAbstractTicket> ticketList = userModel.getTickets();
        //ArrayList<InstantiateAbstractTicket> ticketList =  (ArrayList<InstantiateAbstractTicket>) request.getSession().getAttribute("billTickets");
        
//        if(ticketList != null){         	
        
	        //Substitute tickets from shoppingcart
	        for (InstantiateAbstractTicket  ticket : billTickets){	        	
		        userModel.deleteTicket(ticket);
	        }
//        }
        
        userViewModel = uConverter.convert(userModel);
        model.addAttribute("user", userViewModel);
        model.addAttribute("entry", new LoginViewModel());
        
		return "home";
	}
	
	public void replaceUSerConverter(UserConverter converter) {
		this.userConverter = converter;
	}
}