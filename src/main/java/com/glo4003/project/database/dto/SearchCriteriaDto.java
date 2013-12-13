package com.glo4003.project.database.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.glo4003.project.database.dto.MatchDto.Gender;
import com.glo4003.project.database.dto.MatchDto.Sports;
import com.glo4003.project.global.ModelInterface;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class SearchCriteriaDto implements ModelInterface {
	
	@XStreamOmitField
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA_FRENCH);
	
	private long id = 0L;
	private Sports sport = null;
	private Gender gender = null;
	private String opponent = null;
	private String category = null;
	private String city = null;
	private Date fromDate = null;
	private Date toDate = null;
	private String searchName = "";
	
	public SearchCriteriaDto() {	
		fromDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		toDate = cal.getTime();		
	}
	
	public SearchCriteriaDto(String sport, String gender, String opponent, String category, String city, String fromDate, String toDate) throws ParseException {
		setSport(sport);	
		setGender(gender);				
		setFromDate(fromDate);
		setToDate(toDate);
		setOpponent(opponent);	
		setCity(city);
		setCategory(category);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Sports getSport() {
		return sport;
	}
	
	public Boolean isSportEmpty() {
		if (sport == null) {
			return true;
		}
		return false;
	}
	
	public void setSport(String sport) {
		try {
			this.sport = Sports.valueOf(sport);			
		} catch (Exception e) {			
			this.sport = null;
		}	
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public Boolean isGenderEmpty() {
		if (gender == null || gender.equals("")) {
			return true;
		}
		return false;
	}
	
	public void setGender(String gender) {
		try {
			this.gender = Gender.valueOf(gender);
		} catch (Exception e) {			
			this.gender = null;
		}
	}
	
	public String getOpponent() {
		return opponent;
	}
	
	public Boolean isOpponentEmpty() {
		if (opponent == null || opponent.equals("")) {
			return true;
		}
		return false;
	}
	
	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}
	
	public Boolean isCategoryEmpty() {
		if (category == null || category.equals("")) {
			return true;
		}
		return false;
	}
	
	public String getCity() {
		return city;
	}
	
	public Boolean isCityEmpty() {
		if (city == null || city.equals("")) {
			return true;
		}
		return false;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getFromDate() {
		try {
			return dateFormat.format(fromDate);
		} catch (NullPointerException e) {
			return "";
		}
	}
	
	public Date getFromDateObject() {
		return fromDate;
	}
	
	public Boolean isFromDateEmpty() {
		if (fromDate == null || fromDate.equals("")) {
			return true;
		}
		return false;
	}
	
	public void setFromDate(String fromDate) {
		try {
			this.fromDate = dateFormat.parse(fromDate);
		} catch (Exception e) {			
			this.fromDate = null;
		}
	}
	
	public String getToDate() {
		try {
			return dateFormat.format(toDate);
		} catch (NullPointerException e) {
			return "";
		}
	}
	
	public Date getToDateObject() {
		return toDate;
	}
	
	public Boolean isToDateEmpty() {
		if (toDate == null || toDate.equals("")) {
			return true;
		}
		return false;
	}
	
	public void setToDate(String toDate) {
		try {
			this.toDate = dateFormat.parse(toDate);	
		} catch (Exception e) {			
			this.toDate = null;
		}
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}	
}
