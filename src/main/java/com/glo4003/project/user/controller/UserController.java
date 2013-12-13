package com.glo4003.project.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.exception.SaveException;
import com.glo4003.project.global.ControllerInterface;
import com.glo4003.project.injection.ProviderCreator;
import com.glo4003.project.injection.Resolver;
import com.glo4003.project.user.model.view.LoginViewModel;
import com.glo4003.project.user.model.view.UserViewModel;
import com.glo4003.project.user.service.UserService;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Handles requests for the User logic.
 */
@Controller
public class UserController implements ControllerInterface {	
	
	private UserService userService;
	public Provider<LoginViewModel> loginViewModelProvider;
	public Provider<UserViewModel> userViewModelProvider;
	
	public void dependanciesInjection( UserService userService ){
		this.userService = userService;
		this.loginViewModelProvider = Resolver.getInjectedInstance(ProviderCreator.class).loginViewModelProvider;
		this.userViewModelProvider = Resolver.getInjectedInstance(ProviderCreator.class).userViewModelProvider;
	}
	
	public void dependanciesInjection() {
		this.userService = Resolver.getInjectedInstance(UserService.class);
		this.loginViewModelProvider = Resolver.getInjectedInstance(ProviderCreator.class).loginViewModelProvider;
		this.userViewModelProvider = Resolver.getInjectedInstance(ProviderCreator.class).userViewModelProvider;
	}
	
	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String newUser(Model model) {		
		model.addAttribute("userModel", userViewModelProvider.get());
		model.addAttribute("entry", loginViewModelProvider.get());	
		
		return "newuser";
	}
	
	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String createUser(Model model, UserViewModel viewModel) throws PersistException, ConvertException {		
		try {
			userService.saveNew(viewModel);
		} catch (SaveException e) {
			viewModel.addWarning(e.getErrors());
			model.addAttribute("userModel", viewModel);
			model.addAttribute("entry", loginViewModelProvider.get());
			
			return "newuser";
		}
		
		model.addAttribute("entry", loginViewModelProvider.get());
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
	public String shoppingCart(Model model, HttpServletRequest request) {	
		
		model.addAttribute("user", request.getSession().getAttribute("loggedUser"));
		model.addAttribute("entry", loginViewModelProvider.get());	
		
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

		return "updateUser";
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String modify(Model model, HttpServletRequest request, UserViewModel viewModel) throws PersistException, ConvertException {		
		try {
			userService. modify(viewModel);
		} catch (SaveException e) {
			viewModel.addWarning(e.getErrors());
			model.addAttribute("userModel", viewModel);
			model.addAttribute("entry", loginViewModelProvider.get());
			
			return "updateUser";
		}
		
		request.getSession().setAttribute("loggedUser", viewModel);
		return "home";
	}

}