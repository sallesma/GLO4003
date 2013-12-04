package com.glo4003.project.ticket.viewModel;

import java.util.Date;

import com.glo4003.project.database.model.MatchModel.Sports;

public abstract class InstantiateTicketViewModel {

	private Long id;
	private Long matchId;
	private int catIndex;
	private Date date;
	private Sports sport;
	private String opponent;
	private String city;
	private String field;
	
	public InstantiateTicketViewModel () {
		this.setId(null);
		this.setMatchId(null);
		this.setCatIndex(0);
		this.setSport(null);
		this.setDate(null);
		this.setOpponent("");
		this.setCity("");
		this.setField("");
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}

	public int getCatIndex() {
		return catIndex;
	}

	public void setCatIndex(int catIndex) {
		this.catIndex = catIndex;
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

	public Sports getSport() {
		return sport;
	}

	public void setSport(Sports sport) {
		this.sport = sport;
	}	
}
