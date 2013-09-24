package com.glo4003.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.MatchModel;

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
	
	@RequestMapping(value = "/matchsList", method = RequestMethod.GET)
	public String getMatchList(Model model, HttpServletRequest request) {	
		DbHelper dbHelper = DbHelper.getInstance();
		
		List<Sports> sports = new ArrayList<Sports>(Arrays.asList(Sports.values()));
		model.addAttribute("sports", sports);
		
		List<MatchModel> matchList = new ArrayList<MatchModel>(dbHelper.getAllMatchs());
		if (request.getParameter("sport") != null && !request.getParameter("sport").equals("")) {
			List<MatchModel> filteredMatchList = new ArrayList<MatchModel>();
			String sport = request.getParameter("sport");
			for (MatchModel match : matchList) {
				if (match.getSport().toString().equals(sport))
					filteredMatchList.add(match);
			}
			model.addAttribute("matchs", filteredMatchList);
			if (filteredMatchList.isEmpty())
				model.addAttribute("noMatch", "Il n'y a plus de matchs pour ce sport");
		}
		else
			model.addAttribute("matchs", matchList);
		return "matchsList";
	}
	
	@RequestMapping(value = "/match", method = RequestMethod.GET)
	public String getMatch(Model model, HttpServletRequest request) {	
		DbHelper dbHelper = DbHelper.getInstance();
		int id = Integer.valueOf(request.getParameter("matchID"));
		MatchModel match = dbHelper.getMatchFromId(id);
		model.addAttribute("match", match);
		return "match";
	}
}