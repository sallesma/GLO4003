package helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import model.MatchModel;
import config.ConfigManager.Gender;
import config.ConfigManager.Sports;

public class MatchFilter {
	private List<Sports> sportsList = new ArrayList<Sports>(Arrays.asList(Sports.values()));
	private List<Gender> genderList = new ArrayList<Gender>(Arrays.asList(Gender.values()));
	private Set<String> opponentsList = null;
	
	private List<MatchModel> matchList = null;
	private String sport = null;
	private String gender = null;
	private String opponent = null;
	private String fromDate = null;
	private String toDate = null;

	public MatchFilter(List<MatchModel> matchList) {
		this.matchList = matchList;
		this.opponentsList = new TreeSet<String>();
		for ( MatchModel match : matchList ) {
			opponentsList.add(match.getOpponent());
		}
		
		fromDate = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA_FRENCH).format(new Date());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		Date oneYearLater = cal.getTime();
		toDate = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA_FRENCH).format(oneYearLater);
		
	}

	public MatchFilter(List<MatchModel> matchList, String sport, String gender, String opponent, String fromDate, String toDate) {
		super();
		this.sport = sport;
		this.gender = gender;
		this.opponent = opponent;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.opponentsList = new TreeSet<String>();
		for ( MatchModel match : matchList ) {
			opponentsList.add(match.getOpponent());
		}
		this.matchList = matchList;
	}
	
	public List<MatchModel> filterMatchList() {
		FilterFromSport();
		FilterFromGender();
		FilterFromOpponent();
		FilterFromDate();
		return matchList;
	}

	private void FilterFromSport() {
		if (sport != null && !sport.equals("")) {
			List<MatchModel> newMatchList = new ArrayList<MatchModel>();
			for (MatchModel match : matchList) {
				if (sport.equals(match.getSport().toString()))
					newMatchList.add(match);
			}
			matchList = newMatchList;
		}
	}
	
	private void FilterFromGender() {
		if (gender != null && !gender.equals("")) {
			List<MatchModel> newMatchList = new ArrayList<MatchModel>();
			for( MatchModel match : matchList) {
				if ( gender.equals(match.getGender().toString()) )
					newMatchList.add(match);
			}
			matchList = newMatchList;
		}
	}
	
	private void FilterFromOpponent() {
		if (opponent != null && !opponent.equals("")) {
			List<MatchModel> newMatchList = new ArrayList<MatchModel>();
			for( MatchModel match : matchList) {
				if ( opponent.equals(match.getOpponent()) )
					newMatchList.add(match);
			}
			matchList = newMatchList;
		}
	}
	private void FilterFromDate() {
		if (fromDate != null && !fromDate.equals("")) {
			List<MatchModel> newMatchList = new ArrayList<MatchModel>();
			Date startDate = null;
			Date endDate = null;
			try {
				startDate = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA_FRENCH).parse(fromDate);
				endDate = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA_FRENCH).parse(toDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			for( MatchModel match : matchList) {
				if ( match.getDate().after(startDate) && match.getDate().before(endDate) )
					newMatchList.add(match);
			}
			matchList = newMatchList;
		}
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

	public Set<String> getOpponentsList() {
		return opponentsList;
	}

	public void setOpponentsList(Set<String> opponentsList) {
		this.opponentsList = opponentsList;
	}

	public List<MatchModel> getMatchList() {
		return matchList;
	}

	public void setMatchList(List<MatchModel> matchList) {
		this.matchList = matchList;
	}

	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOpponent() {
		return opponent;
	}

	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	
	
}
