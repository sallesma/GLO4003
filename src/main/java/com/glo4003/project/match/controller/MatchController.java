package com.glo4003.project.match.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.glo4003.project.database.exception.ConvertException;
import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.MatchModel;
import com.glo4003.project.database.model.SearchCriteriaModel;
import com.glo4003.project.match.dao.MatchModelDao;
import com.glo4003.project.match.helper.MatchConverter;
import com.glo4003.project.match.helper.MatchFilter;
import com.glo4003.project.match.model.MatchConcreteModel;
import com.glo4003.project.match.viewModel.MatchViewModel;
import com.glo4003.project.ticket.model.InstantiateAbstractTicket;
import com.glo4003.project.ticket.model.factory.InstantiateTicketFactory;
import com.glo4003.project.user.dao.UserModelDao;
import com.glo4003.project.user.helper.UserConverter;
import com.glo4003.project.user.model.UserConcreteModel;
import com.glo4003.project.user.model.view.LoginViewModel;
import com.glo4003.project.user.model.view.UserViewModel;

/**
 * Handles requests for the Match logic.
 */
@Controller
public class MatchController {
	
	private MatchModelDao matchDao;
	private UserModelDao userDao;
	private UserConverter uConverter;
	private MatchConverter mConverter;
	
	public static final Logger logger = LoggerFactory.getLogger(MatchController.class);
	
	
	public void dependanciesInjection(MatchModelDao matchDao, UserModelDao userDao, MatchConverter mConverter, UserConverter uConverter)
	{
		this.matchDao = matchDao;
		this.userDao = userDao;
		this.uConverter = uConverter;
		this.mConverter = mConverter;
	}
	
	@RequestMapping(value = "/matchsList", method = RequestMethod.GET)
	public String getMatchList(Model model, HttpServletRequest request) throws PersistException {		
		
		List<MatchModel> matchModelDBList = matchDao.getAll();
		ArrayList<MatchConcreteModel> matchModelList = new ArrayList<>();
		for (MatchModel m : matchModelDBList){
			matchModelList.add(mConverter.convertFromDB(m));
		}
		
		ArrayList<MatchViewModel> matchViewModelList = new ArrayList<MatchViewModel>();
		for (MatchConcreteModel m : matchModelList) {
			matchViewModelList.add(mConverter.convertToView(m));
		}
		
		MatchFilter matchFilter = new MatchFilter(matchViewModelList);
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
		UserConcreteModel user = getUser(request);
		
		
		SearchCriteriaModel criterias = new SearchCriteriaModel(
				request.getParameter("criterias.sport"),
				request.getParameter("criterias.gender"), 
				request.getParameter("criterias.opponent"),
				request.getParameter("criterias.category"),
				request.getParameter("criterias.city"),
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
		
		List<MatchModel> matchModelDBList = matchDao.getAll();
		ArrayList<MatchConcreteModel> matchModelList = new ArrayList<>();
		for(MatchModel m : matchModelDBList){
			matchModelList.add(mConverter.convertFromDB(m));
		}
		
		ArrayList<MatchViewModel> matchViewModelList = new ArrayList<MatchViewModel>();
		for (MatchConcreteModel m : matchModelList) {
			matchViewModelList.add(mConverter.convertToView(m));
		}
		
		MatchFilter matchFilter = new MatchFilter(matchViewModelList, criterias);
		String mustSave = request.getParameter("mustSave");			
		if((mustSave != null) && !mustSave.isEmpty()) {		
			
			criterias.setSearchName(mustSave);
			user.addSearchCriteria(criterias);
			try {			
				userDao.save(uConverter.convertToDB(user));				
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

	  private List<SearchCriteriaModel> getUserCriterias(HttpServletRequest request) throws PersistException {
		UserConcreteModel user = getUser(request);
		if (user == null) {
			return new ArrayList<SearchCriteriaModel>(0);
		}
		
		return user.getSearchCriteria();
	}

	@RequestMapping(value = "/match", method = RequestMethod.GET)
      public String getMatch(Model model, HttpServletRequest request) throws PersistException {		  
          Long id = Long.valueOf(request.getParameter("matchID"));
          
          MatchModel matchDB = matchDao.getById(id);
          MatchConcreteModel match = mConverter.convertFromDB(matchDB);
          MatchViewModel mViewModel = mConverter.convertToView(match);
          
          model.addAttribute("match", mViewModel);
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
              UserConcreteModel userConcreteModel = uConverter.convertFromView(userViewModel);

              // Add the ticket to the user's shopping cart
              userConcreteModel.addTicket(ticket);
              userViewModel = uConverter.convertToView(userConcreteModel);
           
              request.getSession().setAttribute("loggedUser", userViewModel);
              model.addAttribute("user", userViewModel);
              model.addAttribute("entry", new LoginViewModel());
          }
          return "shoppingCart";
      }
	  
	  
	  private UserConcreteModel getUser(HttpServletRequest request) throws PersistException {
		  UserViewModel userViewModel = (UserViewModel) request.getSession().getAttribute("loggedUser");
		  if(userViewModel == null) {
			  return null;
		  }
          
          return uConverter.convertFromView(userViewModel);
	  }

}