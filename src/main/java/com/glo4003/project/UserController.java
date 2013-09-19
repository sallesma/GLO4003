package com.glo4003.project;

import helper.UserConverter;

import java.util.Locale;

import model.UserModel;
import model.UserViewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.UserService;
import exceptions.SaveException;

/**
 * Handles requests for the User logic.
 */
@Controller
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/new/user", method = RequestMethod.GET)
	public String newUser(Locale locale, Model model) {		
		model.addAttribute("entry", new UserViewModel());
		
		return "user.new";
	}
	
	@RequestMapping(value = "/new/user", method = RequestMethod.POST)
	public String create(UserViewModel viewModel, Model model) {
		UserModel user = UserConverter.convert(viewModel);
		
		try {
			userService.saveNew(user);
		} catch (SaveException e) {
			viewModel.addWarning(e.getMessage());
			model.addAttribute("entry", viewModel);
			
			return "user.new";
		}
		
		return "redirect:/";
	}

}