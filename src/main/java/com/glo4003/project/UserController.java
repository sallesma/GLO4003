package com.glo4003.project;

import java.util.Locale;

import model.UserViewModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.UserService;
import exceptions.SaveException;

/**
 * Handles requests for the User logic.
 */
@Controller
public class UserController {
	
	public static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;	
	
	@RequestMapping(value = "/new/user", method = RequestMethod.GET)
	public String newUser(Locale locale, Model model) {		
		model.addAttribute("entry", new UserViewModel());		
		
		return "user.new";
	}
	
	@RequestMapping(value = "/new/user", method = RequestMethod.POST)
	public ModelAndView create(UserViewModel viewModel) {		
		try {
			userService.saveNew(viewModel);
		} catch (SaveException e) {
			viewModel.addWarning(e.getErrors());						
			return new ModelAndView("user.new","entry", viewModel);
		}
		
		return new ModelAndView("home","entry", viewModel);
	}

}