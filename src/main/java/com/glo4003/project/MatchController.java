package com.glo4003.project;

import helper.MatchFilterV2;
import helper.UserConverter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.InstantiateAbstractTicket;
import model.LoginViewModel;
import model.MatchModel;
import model.SearchCriteriaModel;
import model.UserModel;
import model.UserViewModel;
import model.factory.InstantiateTicketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import database.dao.MatchModelDao;
import database.dao.UserModelDao;
import exceptions.ConvertException;
import exceptions.PersistException;

/**
 * Handles requests for the Match logic.
 */
@Controller
public class MatchController {
	
	private MatchModelDao matchDao = new MatchModelDao();
	private UserModelDao userDao = new UserModelDao();

	public static final Logger logger = LoggerFactory.getLogger(MatchController.class);
	private UserConverter uConverter;
	
	@RequestMapping(value = "/matchsList", method = RequestMethod.GET)
	public String getMatchList(Model model, HttpServletRequest request) throws PersistException {		

		MatchFilterV2 matchFilter = new MatchFilterV2();
		matchFilter.filterMatchList();
		model.addAttribute("filter", matchFilter);
		
		List<SearchCriteriaModel> customCriterias = getUserCriterias(request);
		model.addAttribute("customCriterias",customCriterias);
		model.addAttribute("customCriteria","");

		model.addAttribute("entry", new LoginViewModel());
		
		return "matchsList";
	}

	@RequestMapping(value = "/matchsList", method = RequestMethod.POST)
	public String getPostMatchList(Model model, HttpServletRequest request) 
			throws PersistException, ParseException {		
		UserModel user = getUser(request);
		
		
		SearchCriteriaModel criterias = new SearchCriteriaModel(
				request.getParameter("criterias.sport"),
				request.getParameter("criterias.gender"), 
				request.getParameter("criterias.opponent"), 
				request.getParameter("criterias.fromDate"), 
				request.getParameter("criterias.toDate"));		
		
		String criteria = request.getParameter("customCriteria");
		if ((criteria != null) && (!criteria.isEmpty())) {			
			for (SearchCriteriaModel oneCriteria : user.getSearchCriteria()) {
				if (oneCriteria.getSearchName().contentEquals(criteria)) {
					criterias = oneCriteria;
				}
			}
		}
		MatchFilterV2 matchFilter = new MatchFilterV2(criterias);
		String mustSave = request.getParameter("mustSave");			
		if((mustSave != null) && !mustSave.isEmpty()) {		
			
			criterias.setSearchName(mustSave);
			user.addSearchCriteria(criterias);
			try {			
				userDao.save(user);				
			} catch (ConvertException e) {
				e.printStackTrace();
			}
			model.addAttribute("loggedUser", user);			
			//model.addAttribute("customCriteria","");
		}		

		matchFilter.filterMatchList();
		if (matchFilter.getMatchList().isEmpty())
			model.addAttribute("noMatch", "Il n'y a pas de match disponible selon les filtres choisis");
		
		model.addAttribute("filter", matchFilter);
		model.addAttribute("entry", new LoginViewModel());
		if (user != null) {
			model.addAttribute("customCriterias",user.getSearchCriteria());
		}
		
		return "matchsList";
	}

	  private List<SearchCriteriaModel> getUserCriterias(HttpServletRequest request) {
		UserModel user = getUser(request);
		if (user == null) {
			return new ArrayList<SearchCriteriaModel>(0);
		}
		
		return user.getSearchCriteria();
	}

	@RequestMapping(value = "/match", method = RequestMethod.GET)
      public String getMatch(Model model, HttpServletRequest request) throws PersistException {		  
          Long id = Long.valueOf(request.getParameter("matchID"));
          MatchModel match = matchDao.getById(id);
          model.addAttribute("match", match);
          model.addAttribute("entry", new LoginViewModel());
          return "match";
      }
	  
	  @RequestMapping(value = "/add", method = RequestMethod.GET)
      public String addTicketToShoppingCart(Model model, HttpServletRequest request) throws PersistException, ConvertException {
		  // Retrieve Request parameters 
          Long matchId = Long.valueOf(request.getParameter("matchID"));
          int catId = Integer.valueOf(request.getParameter("catID"));
          String numPlace = request.getParameter("place");
          int nbPlace = (request.getParameter("nbPlace")) != null ? (Integer.valueOf(request.getParameter("nbPlace"))) : 1 ;
          
          // Retrieve selected match from Db          
          MatchModel match = matchDao.getById(matchId);
          
          // Create the ticket for the specific match
          InstantiateAbstractTicket ticket = InstantiateTicketFactory.getInstanciateTickets(catId, match, numPlace, nbPlace);
          
          if (ticket == null) {
        	  model.addAttribute("nullTicket", "Il ne reste pas assez de tickets disponibles");
          }
          
          else {
        	  // Save the match in the database
              matchDao.save(match);
              
              //Get current logged user
              UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("loggedUser");
              uConverter = new UserConverter();
              UserModel userModel = uConverter.convert(userViewModel);

              // Add the ticket to the user's shopping cart
              userModel.addTicket(ticket);
              userViewModel = uConverter.convert(userModel);
           
              model.addAttribute("user", userViewModel);
              model.addAttribute("entry", new LoginViewModel());
          }
          return "shoppingCart";
      }
	  
	  
	  private UserModel getUser(HttpServletRequest request) {
		  UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("loggedUser");
		  if(userViewModel == null) {
			  return null;
		  }
          uConverter = new UserConverter();
          
          return uConverter.convert(userViewModel);
	  }

}