package com.glo4003.project;

import javax.servlet.http.Cookie;
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
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gargoylesoftware.htmlunit.javascript.host.Console;

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
	public ModelAndView login(Model model, LoginViewModel viewModel, HttpServletResponse response) { 
		
		if(userService.isLoginValid(viewModel.getUsername(), viewModel.getPassword())) {
			Cookie cookie = new Cookie("LOGIN", viewModel.getUsername());
			cookie.setMaxAge(1800);
			response.addCookie(cookie);
			UserModel userModel = userService.getUser(viewModel.getUsername());
			 
			model.addAttribute("connectData", userService.convert(userModel));		
			
			return new ModelAndView("home", "entry", model);
		}		
		
		return new ModelAndView("home", "entry", viewModel);
	}
	
	@RequestMapping(value = "/disconnect", method = RequestMethod.GET)
	public String disconnect(Model model, HttpServletRequest request, HttpServletResponse response) {		
		for (Cookie cookie: request.getCookies()) { 
			if ("LOGIN".contentEquals(cookie.getName())) { 
				cookie.setMaxAge(0);
				cookie.setValue("");
				response.addCookie(cookie);
			} 
		}	
		model.addAttribute("connectData", null);		
		return "redirect:";
	}

}