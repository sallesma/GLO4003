package com.glo4003.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import database.DbHelper;

/**
 * Handles requests for the Match logic.
 */
@Controller
public class MatchController {
	
	public static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/matchs", method = RequestMethod.GET)
	public String matchs(Model model) {	
		DbHelper dbHelper = DbHelper.getInstance();
		model.addAttribute("matchs", dbHelper.getAllMatchs());
		return "matchs";
	}
}