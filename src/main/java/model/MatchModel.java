package model;

import java.util.Date;

import config.ConfigManager.*;

public class MatchModel {
	
	private Sports sport;
	private Genre gender;
	private int matchID;
	private Date date;
	private String city;
	private String terrain;
	private int vipRemainingTickets;
	private int normalRemainingTickets;
	
	
	public MatchModel () {
		this.setSport(null);
		this.setGender(null);
		this.setDate(null);
		this.setCity("");
		this.setTerrain("");
		this.setVipRemainingTickets(0);
		this.setNormalRemainingTickets(0);
	}
	
	
	// Getter and Setter
	public int getMatchID() {
		return matchID;
	}
	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTerrain() {
		return terrain;
	}
	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	public int getVipRemainingTickets() {
		return vipRemainingTickets;
	}
	public void setVipRemainingTickets(int vipRemainingTickets) {
		this.vipRemainingTickets = vipRemainingTickets;
	}
	public int getNormalRemainingTickets() {
		return normalRemainingTickets;
	}
	public void setNormalRemainingTickets(int normalRemainingTickets) {
		this.normalRemainingTickets = normalRemainingTickets;
	}
	
	
	public Sports getSport() {
		return sport;
	}


	public void setSport(Sports sport) {
		this.sport = sport;
	}


	public Genre getGender() {
		return gender;
	}


	public void setGender(Genre gender) {
		this.gender = gender;
	}
}
