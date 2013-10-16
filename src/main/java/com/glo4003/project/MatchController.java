package com.glo4003.project;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.LoginViewModel;
import model.MatchFilter;
import model.MatchModel;

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

	public static final Logger logger = LoggerFactory.getLogger(MatchController.class);

	@RequestMapping(value = "/matchsList", method = RequestMethod.GET)
	public String getMatchList(Model model, HttpServletRequest request) {
		DbHelper dbHelper = DbHelper.getInstance();

		List<MatchModel> matchList = new ArrayList<MatchModel>(dbHelper.getAllMatchs());
		MatchFilter matchFilter = new MatchFilter(matchList);
		model.addAttribute("filter", matchFilter);

		model.addAttribute("entry", new LoginViewModel());
		return "matchsList";
	}

	@RequestMapping(value = "/matchsList", method = RequestMethod.POST)
	public String getPostMatchList(Model model, HttpServletRequest request) {
		DbHelper dbHelper = DbHelper.getInstance();

		List<MatchModel> matchList = new ArrayList<MatchModel>(dbHelper.getAllMatchs());
		MatchFilter matchFilter = new MatchFilter(matchList, request.getParameter("sport"), request.getParameter("gender"),
				request.getParameter("opponent"), request.getParameter("fromDate"), request.getParameter("toDate"));

		if (matchFilter.getMatchList().isEmpty())
			model.addAttribute("noMatch", "Il n'y a pas de matchs proposés selon les filtres choisis");

		model.addAttribute("filter", matchFilter);
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