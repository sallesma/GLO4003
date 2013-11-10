package com.glo4003.project;

import javax.servlet.http.HttpServletRequest;

import model.LoginViewModel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PaymentController {
	@RequestMapping(value = "/bill", method = RequestMethod.GET)
	public String bill(Model model, HttpServletRequest request) {	
		
		model.addAttribute("user", request.getSession().getAttribute("loggedUser"));
		model.addAttribute("entry", new LoginViewModel());	
		
		return "bill";
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public String payment(Model model, HttpServletRequest request) {	
		
		model.addAttribute("user", request.getSession().getAttribute("loggedUser"));
		model.addAttribute("entry", new LoginViewModel());	
		
		return "payment";
	}
}