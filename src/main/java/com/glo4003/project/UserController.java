package com.glo4003.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginViewModel;
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
import exceptions.ConvertException;
import exceptions.PersistException;
import exceptions.SaveException;

/**
 * Handles requests for the User logic.
 */
@Controller
public class UserController {	
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String newUser(Model model) {		
		model.addAttribute("userModel", new UserViewModel());
		model.addAttribute("entry", new LoginViewModel());	
		
		return "newuser";
	}
	
	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String createUser(Model model, UserViewModel viewModel) throws PersistException, ConvertException {		
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
	public ModelAndView login(Model model, LoginViewModel viewModel, HttpServletRequest request) throws PersistException { 
		List<String> warnings = userService.validate(viewModel.getUsername(), viewModel.getPassword());
		
		if(warnings.isEmpty()) {
			UserViewModel userModel = userService.convert(userService.getUser(viewModel.getUsername()));
			request.getSession().setAttribute("loggedUser", userModel);
			
			return new ModelAndView("home", "entry", model);
		}
		
		viewModel.addWarning(warnings);
		
		return new ModelAndView("home", "entry", viewModel);
	}
	
	
	@RequestMapping(value = "/shoppingCart", method = RequestMethod.GET)
	public String shoppingCard(Model model, HttpServletRequest request) {	
		
		model.addAttribute("user", request.getSession().getAttribute("loggedUser"));
		model.addAttribute("entry", new LoginViewModel());	
		
		return "shoppingCart";
	}
	
	@RequestMapping(value = "/disconnect", method = RequestMethod.GET)
	public String disconnect(Model model, HttpServletRequest request, HttpServletResponse response) {		
		request.getSession().setAttribute("loggedUser", null);
		return "redirect:";
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	public String updateUser(Model model,HttpServletRequest request) {
		model.addAttribute("userModel", request.getSession().getAttribute("loggedUser"));
//		model.addAttribute("entry", new LoginViewModel());

		return "updateUser";
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String modify(Model model, HttpServletRequest request, UserViewModel viewModel) throws PersistException, ConvertException {		
		try {
			userService. modify(viewModel);
		} catch (SaveException e) {
			viewModel.addWarning(e.getErrors());
			model.addAttribute("userModel", viewModel);
			model.addAttribute("entry", new LoginViewModel());
			
			return "updateUser";
		}
		
		request.getSession().setAttribute("loggedUser", viewModel);
		return "home";
	}

}