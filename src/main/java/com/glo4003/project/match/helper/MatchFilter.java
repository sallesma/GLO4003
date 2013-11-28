package com.glo4003.project.match.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.AbstractTicketCategory;
import com.glo4003.project.database.model.GeneralAdmissionTicketCategory;
import com.glo4003.project.database.model.MatchModel.Gender;
import com.glo4003.project.database.model.MatchModel.Sports;
import com.glo4003.project.database.model.ReservedTicketCategory;
import com.glo4003.project.database.model.SearchCriteriaModel;

public class MatchFilter {
	
	private final List<String> ticketCategory;
	private List<Sports> sportsList = new ArrayList<Sports>(Arrays.asList(Sports.values()));
	private List<Gender> genderList = new ArrayList<Gender>(Arrays.asList(Gender.values()));
	private Set<String> cityList = null;
	private Set<String> opponentsList = null;
	private List<MatchViewModel> matchList = null;
	private SearchCriteriaModel criterias = null;	
	private String customCriteria = "";

	public MatchFilter(List<MatchViewModel> list) {
		matchList = list;
		criterias = new SearchCriteriaModel();
		ticketCategory = new ArrayList<String>(2);
		ticketCategory.add("Reserve");
		ticketCategory.add("General");		
	}

	public MatchFilter(List<MatchViewModel> list, SearchCriteriaModel model) throws PersistException {
		matchList = list;
		criterias = model;	
		ticketCategory = new ArrayList<String>(2);
		ticketCategory.add("Reserve");
		ticketCategory.add("General");	
	}
	
	public List<MatchViewModel> filterMatchList() throws PersistException {		
		this.opponentsList = new TreeSet<String>();
		for ( MatchViewModel match : matchList ) {
			opponentsList.add(match.getOpponent());
		}
		this.cityList = new TreeSet<String>();
		for ( MatchViewModel match : matchList ) {
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
			List<MatchViewModel> newMatchList = new ArrayList<MatchViewModel>();
			for (MatchViewModel match : matchList) {
				if (criterias.getCity().equals(match.getCity())) {
					newMatchList.add(match);
				}
			}
			matchList = newMatchList;
		}
	}
	
	private void filterByTicketType() {
		if (!criterias.isCategoryEmpty()) {
			List<MatchViewModel> newMatchList = new ArrayList<MatchViewModel>();
			for (MatchViewModel match : matchList) {				
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
			List<MatchViewModel> newMatchList = new ArrayList<MatchViewModel>();
			for (MatchViewModel match : matchList) {
				if (criterias.getSport().equals(match.getSport())) {
					newMatchList.add(match);
				}
			}
			matchList = newMatchList;
		}
	}
	
	private void FilterFromGender() {
		if (!criterias.isGenderEmpty()) {
			List<MatchViewModel> newMatchList = new ArrayList<MatchViewModel>();
			for( MatchViewModel match : matchList) {
				if (criterias.getGender().equals(match.getGender()) ) {
					newMatchList.add(match);
				}
			}
			matchList = newMatchList;
		}
	}
	
	private void FilterFromOpponent() {
		if (!criterias.isOpponentEmpty()) {
			List<MatchViewModel> newMatchList = new ArrayList<MatchViewModel>();
			for( MatchViewModel match : matchList) {
				if (criterias.getOpponent().contentEquals(match.getOpponent())) {
					newMatchList.add(match);
				}
			}
			matchList = newMatchList;
		}
	}
	private void FilterFromDate() {
		if (!criterias.isFromDateEmpty() && !criterias.isToDateEmpty()) {
			List<MatchViewModel> newMatchList = new ArrayList<MatchViewModel>();
			
			for( MatchViewModel match : matchList) {
				if ( match.getDate().after(criterias.getFromDateObject()) 
						&& match.getDate().before(criterias.getToDateObject()))
					newMatchList.add(match);
			}
			matchList = newMatchList;
		}
	}

	public List<MatchViewModel> getMatchList() {
		return matchList;
	}

	public List<String> getTicketCategory() {
		return ticketCategory;
	}

	public void setMatchList(List<MatchViewModel> matchList) {
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
