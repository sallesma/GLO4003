package com.glo4003.project;

import helper.UserConverter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.InstantiateAbstractTicket;
import model.InstantiateTicketModel;
import model.LoginViewModel;
import model.UserModel;
import model.UserViewModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingCartController {
	private UserConverter userConverter;


	@RequestMapping(value = "/selectedTicketsAction", method = RequestMethod.POST)
	public String handlePosts(Model model, HttpServletRequest request, @RequestParam String action) {	

		UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("loggedUser");
		userConverter = new UserConverter();
		UserModel userModel = userConverter.convert(userViewModel);

		if( action.equals("buy") ){


			List<InstantiateAbstractTicket> billTickets = new ArrayList<InstantiateAbstractTicket>();

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
		else if( action.equals("delete") ){

			String[] selectedTickets =  request.getParameterValues("ticketId");
			if (selectedTickets == null) {
				model.addAttribute("noTicket", "Il n'y a pas de billets sélectionnés, d");
			} else {
				for ( String selectedTicket : selectedTickets ) {
					userModel.deleteTicket(Integer.valueOf(selectedTicket));
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

	  @RequestMapping(value = "/emptyCart", method = RequestMethod.GET)
			public String emptyCart(Model model, HttpServletRequest request) {
			  UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("loggedUser");
			  userConverter = new UserConverter();
	          UserModel userModel = userConverter.convert(userViewModel);
	          
	          userModel.emptyCart();
	          
	          return "redirect:/";
	}
	  
	public void replaceUSerConverter(UserConverter converter) {
		this.userConverter = converter;
	}
}