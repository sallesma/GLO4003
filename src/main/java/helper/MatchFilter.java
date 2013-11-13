package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.AbstractTicketCategory;
import model.GeneralAdmissionTicketCategory;
import model.MatchModel;
import model.ReservedTicketCategory;
import model.SearchCriteriaModel;
import config.ConfigManager.Gender;
import config.ConfigManager.Sports;
import database.dao.MatchModelDao;
import exceptions.PersistException;

public class MatchFilter {
	
	private final List<String> ticketCategory;	
	private MatchModelDao matchDao = new MatchModelDao();	
	private List<Sports> sportsList = new ArrayList<Sports>(Arrays.asList(Sports.values()));
	private List<Gender> genderList = new ArrayList<Gender>(Arrays.asList(Gender.values()));
	private Set<String> cityList = null;
	private Set<String> opponentsList = null;
	private List<MatchModel> matchList = null;
	private SearchCriteriaModel criterias = null;	
	private String customCriteria = "";

	public MatchFilter() {		
		criterias = new SearchCriteriaModel();
		ticketCategory = new ArrayList<String>(2);
		ticketCategory.add("Reserve");
		ticketCategory.add("General");		
	}

	public MatchFilter(SearchCriteriaModel model) throws PersistException {		
		criterias = model;	
		ticketCategory = new ArrayList<String>(2);
		ticketCategory.add("Reserve");
		ticketCategory.add("General");	
	}
	
	public List<MatchModel> filterMatchList() throws PersistException {		
		matchList = matchDao.getAll();
		this.opponentsList = new TreeSet<String>();
		for ( MatchModel match : matchList ) {
			opponentsList.add(match.getOpponent());
		}
		this.cityList = new TreeSet<String>();
		for ( MatchModel match : matchList ) {
			cityList.add(match.getCity().trim());		
		}
		
		FilterFromSport();
		FilterFromGender();
		FilterFromOpponent();
		FilterFromDate();
		filterByCity();
		filterByTicketType();
		
		return matchList;
	}
	
	private void filterByCity() {
		if (!criterias.isCityEmpty()) {
			List<MatchModel> newMatchList = new ArrayList<MatchModel>();
			for (MatchModel match : matchList) {
				if (criterias.getCity().equals(match.getCity())) {
					newMatchList.add(match);
				}
			}
			matchList = newMatchList;
		}
	}
	
	private void filterByTicketType() {
		if (!criterias.isCategoryEmpty()) {
			List<MatchModel> newMatchList = new ArrayList<MatchModel>();
			for (MatchModel match : matchList) {				
				for (AbstractTicketCategory ticket : match.getTickets())
					if (criterias.getCategory().contentEquals("Reserve")) {
						if (ticket instanceof ReservedTicketCategory) {
							newMatchList.add(match);							
						}						
					} else if (criterias.getCategory().contentEquals("General")){
						if (ticket instanceof GeneralAdmissionTicketCategory) {
							newMatchList.add(match);
						}						
					}
			}
			matchList = newMatchList;
		}
	}

	private void FilterFromSport() {
		if (!criterias.isSportEmpty()) {
			List<MatchModel> newMatchList = new ArrayList<MatchModel>();
			for (MatchModel match : matchList) {
				if (criterias.getSport().equals(match.getSport())) {
					newMatchList.add(match);
				}
			}
			matchList = newMatchList;
		}
	}
	
	private void FilterFromGender() {
		if (!criterias.isGenderEmpty()) {
			List<MatchModel> newMatchList = new ArrayList<MatchModel>();
			for( MatchModel match : matchList) {
				if (criterias.getGender().equals(match.getGender()) ) {
					newMatchList.add(match);
				}
			}
			matchList = newMatchList;
		}
	}
	
	private void FilterFromOpponent() {
		if (!criterias.isOpponentEmpty()) {
			List<MatchModel> newMatchList = new ArrayList<MatchModel>();
			for( MatchModel match : matchList) {
				if (criterias.getOpponent().contentEquals(match.getOpponent())) {
					newMatchList.add(match);
				}
			}
			matchList = newMatchList;
		}
	}
	private void FilterFromDate() {
		if (!criterias.isFromDateEmpty() && !criterias.isToDateEmpty()) {
			List<MatchModel> newMatchList = new ArrayList<MatchModel>();
			
			for( MatchModel match : matchList) {
				if ( match.getDate().after(criterias.getFromDateObject()) 
						&& match.getDate().before(criterias.getToDateObject()))
					newMatchList.add(match);
			}
			matchList = newMatchList;
		}
	}

	public List<MatchModel> getMatchList() {
		return matchList;
	}

	public List<String> getTicketCategory() {
		return ticketCategory;
	}

	public void setMatchList(List<MatchModel> matchList) {
		this.matchList = matchList;
	}

	public List<Sports> getSportsList() {
		return sportsList;
	}

	public void setSportsList(List<Sports> sportsList) {
		this.sportsList = sportsList;
	}

	public List<Gender> getGenderList() {
		return genderList;
	}

	public void setGenderList(List<Gender> genderList) {
		this.genderList = genderList;
	}

	public SearchCriteriaModel getCriterias() {
		return criterias;
	}

	public void setCriterias(SearchCriteriaModel criterias) {
		this.criterias = criterias;
	}

	public Set<String> getOpponentsList() {
		return opponentsList;
	}

	public void setOpponentsList(Set<String> opponentsList) {
		this.opponentsList = opponentsList;
	}

	public String getCustomCriteria() {
		return customCriteria;
	}

	public void setCustomCriteria(String criteria) {
		this.customCriteria = criteria;
	}

	public Set<String> getCityList() {
		return cityList;
	}

	public void setCityList(Set<String> cityList) {
		this.cityList = cityList;
	}
}
