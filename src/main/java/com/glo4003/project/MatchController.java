package com.glo4003.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import config.ConfigManager.Sports;
import database.DbHelper;

/**
 * Handles requests for the Match logic.
 */
@Controller
public class MatchController {
	
	public static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/matchs", method = RequestMethod.GET)
	public String getMatchs(Model model) {	
		DbHelper dbHelper = DbHelper.getInstance();
		model.addAttribute("matchs", dbHelper.getAllMatchs());
		
		List<Sports> sports = new ArrayList<Sports>(Arrays.asList(Sports.values()));
		model.addAttribute("sports", sports);
		return "matchs";
	}
	
//	@RequestMapping(value = "/matchs", method = RequestMethod.POST)
//	public String postMatchs(Model model) {	
//		DbHelper dbHelper = DbHelper.getInstance();
//		model.addAttribute("matchs", dbHelper.getAllMatchs());
//		return "matchs";
//	}
}