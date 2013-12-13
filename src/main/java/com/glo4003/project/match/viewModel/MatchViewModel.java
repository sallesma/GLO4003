package com.glo4003.project.match.viewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.glo4003.project.database.dto.AbstractTicketCategory;
import com.glo4003.project.database.dto.MatchDto.Gender;
import com.glo4003.project.database.dto.MatchDto.Sports;

public class MatchViewModel {
	
	private Long id = 0L;
	private Sports sport;
	private Gender gender;
	private Date date;
	private String opponent;
	private String city;
	private String field;
	private int numberRemainingTickets;
	private List<AbstractTicketCategory> tickets;
	
	
	public MatchViewModel () {
		this.setSport(null);
		this.setGender(null);
		this.setDate(null);
		this.setOpponent("");
		this.setCity("");
		this.setField("");
		this.setTickets(new ArrayList<AbstractTicketCategory>());
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


	public void setSport(Sports sport) {
		this.sport = sport;
	}


	public Gender getGender() {
		return gender;
	}


	public void setGender(Gender gender) {
		this.gender = gender;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getOpponent() {
		return opponent;
	}


	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getField() {
		return field;
	}


	public void setField(String field) {
		this.field = field;
	}


	public int getNumberRemainingTickets() {
		return numberRemainingTickets;
	}


	public void setNumberRemainingTickets(int numberRemainingTickets) {
		this.numberRemainingTickets = numberRemainingTickets;
	}


	public List<AbstractTicketCategory> getTickets() {
		return tickets;
	}


	public void setTickets(List<AbstractTicketCategory> tickets) {
		this.tickets = tickets;
	}
	
}
	