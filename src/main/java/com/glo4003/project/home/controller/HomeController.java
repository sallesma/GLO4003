package com.glo4003.project.home.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glo4003.project.global.ControllerInterface;
import com.glo4003.project.user.model.view.LoginViewModel;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController implements ControllerInterface {
	
	private String att;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {		
		
		System.out.println(att);
		model.addAttribute("entry", new LoginViewModel());	
		return "home";
	}

	@Override
	public void dependanciesInjection() {
		
	}
	
}
