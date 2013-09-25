package com.glo4003.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.LoginViewModel;
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

		Date currentDate = new Date();
		List<MatchModel> matchList = new ArrayList<MatchModel>(dbHelper.getAllMatchs());
		List<MatchModel> filteredMatchList = new ArrayList<MatchModel>();

		if (request.getParameter("sport") != null && !request.getParameter("sport").equals("")) {
			String sport = request.getParameter("sport");
			for (MatchModel match : matchList) {
				if (match.getSport().toString().equals(sport) && match.getDate().after(currentDate))
					filteredMatchList.add(match);
			}
		} else {
			for (MatchModel match : matchList) {
				if (match.getDate().after(currentDate))
					filteredMatchList.add(match);
			}
		}
		model.addAttribute("matchs", filteredMatchList);

		if (filteredMatchList.isEmpty())
			model.addAttribute("noMatch", "Il n'y a plus de matchs pour ce sport");
		model.addAttribute("entry", new LoginViewModel());
		return "matchsList";
	}

	@RequestMapping(value = "/match", method = RequestMethod.GET)
	public String getMatch(Model model, HttpServletRequest request) {
		DbHelper dbHelper = DbHelper.getInstance();
		int id = Integer.valueOf(request.getParameter("matchID"));
		MatchModel match = dbHelper.getMatchFromId(id);
		model.addAttribute("match", match);
		model.addAttribute("entry", new LoginViewModel());
		return "match";
	}
}