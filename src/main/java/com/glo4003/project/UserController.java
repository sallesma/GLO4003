package com.glo4003.project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginViewModel;
import model.UserModel;
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
	
	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String newUser(Model model) {		
		model.addAttribute("userModel", new UserViewModel());
		model.addAttribute("entry", new LoginViewModel());	
		
		return "newuser";
	}
	
	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String create(Model model, UserViewModel viewModel) {		
		try {
			userService.saveNew(viewModel);
		} catch (SaveException e) {
			viewModel.addWarning(e.getErrors());
			model.addAttribute("userModel", viewModel);
			model.addAttribute("entry", new LoginViewModel());
			
			return "newuser";
		}
		
		model.addAttribute("entry", new LoginViewModel());
		return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView login(Model model, LoginViewModel viewModel, HttpServletRequest request) { 
		
		if(userService.isLoginValid(viewModel.getUsername(), viewModel.getPassword())) {
			UserViewModel userModel = userService.convert(userService.getUser(viewModel.getUsername()));
			request.getSession().setAttribute("loggedUser", userModel);
			
			return new ModelAndView("home", "entry", model);
		}
		return new ModelAndView("home", "entry", viewModel);
	}
	
	@RequestMapping(value = "/disconnect", method = RequestMethod.GET)
	public String disconnect(Model model, HttpServletRequest request, HttpServletResponse response) {		
		request.getSession().setAttribute("loggedUser", null);
		return "redirect:";
	}
	
//	@RequestMapping(value = "/settings", method = RequestMethod.GET)
//	public String editUser(Model model) {
//		model.addAttribute("userModel", new UserViewModel());
//		model.addAttribute("entry", new LoginViewModel());
//
//		return "newuser";
//	}

}