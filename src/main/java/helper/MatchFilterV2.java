package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import config.ConfigManager.Gender;
import config.ConfigManager.Sports;
import model.MatchModel;
import model.SearchCriteriaModel;
import database.dao.MatchModelDao;
import database.dao.MatchModelDaoInterface;
import exceptions.PersistException;

public class MatchFilterV2 {
	
	private MatchModelDaoInterface matchDao = new MatchModelDao();
	private List<Sports> sportsList = new ArrayList<Sports>(Arrays.asList(Sports.values()));
	private List<Gender> genderList = new ArrayList<Gender>(Arrays.asList(Gender.values()));
	private Set<String> opponentsList = null;
	private List<MatchModel> matchList = null;
	private SearchCriteriaModel criterias = null;	
	private String customCriteria = "";

	public MatchFilterV2() {		
		criterias = new SearchCriteriaModel();
	}

	public MatchFilterV2(SearchCriteriaModel model) throws PersistException {		
		criterias = model;		
	}
	
	public List<MatchModel> filterMatchList() throws PersistException {		
		matchList = matchDao.getAll();
		this.opponentsList = new TreeSet<String>();
		for ( MatchModel match : matchList ) {
			opponentsList.add(match.getOpponent());
		}
		
		FilterFromSport();
		FilterFromGender();
		FilterFromOpponent();
		FilterFromDate();		
		
		return matchList;
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

	public void setMatchDao(MatchModelDaoInterface matchDao) {
		this.matchDao = matchDao;
	}
